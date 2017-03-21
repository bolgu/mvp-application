package app.microservice

import app.admin.jobsboard.Job
import app.admin.jobsboard.Publisher
import app.admin.jobsboard.Tag
import app.admin.jobsboard.Type
import grails.transaction.Transactional
import wslite.rest.RESTClient

@Transactional
class ImportGitHubJobsService {

    def utilityService
    static transactional = false

    public static final String GITHUB_JOBS_URL = "https://jobs.github.com"

    def importEuropeanJobsFromGitHub() {
        importJobsFromGitHub(location: "europe")
    }

    def importJobsFromGitHub(data) {

        def location = data.location

        def savedJobs = []

        def client = new RESTClient(GITHUB_JOBS_URL)
        def response = client.get(path:"/positions.json?location=${location}",
                query:[type:'remote', include_entities:true],
                connectTimeout: 5000,
                readTimeout: 10000,
                followRedirects: false,
                useCaches: false,
                sslTrustAllCerts: true)
        log.info "GitHub response status: " + response.statusCode

        response.json.each { entry ->

            def newJob = insertJobInRepository(entry)
            if(newJob.id) {
                savedJobs.add(newJob)
            }
        }

        log.info "Import finished ${new Date()}! Saved ${savedJobs.size()} from ${response.json.size()}!"

        return [
                statusCode: response.statusCode,
                jobsCounter: response.json.size(),
                savedJobs: savedJobs.size()
        ]
    }

    def insertJobInRepository(entry) {
        def logo = entry.company_logo ? utilityService.uriToImage(entry.company_logo) : null
        def newPublisher = new Publisher(
                name: entry.company,
                logo: logo,
                url: entry.company_url,
                location: entry.location
        )

        try {
            if (!newPublisher.save(failOnError: true, validate: true, flush: true)) {
                newPublisher.errors.allErrors.each {
                    log.debug it
                }
            }
        } catch (e) {
            // just if the publisher exists in database (unique constraint is broken) then use the existing publisher for the current job
            def existingPubisher = Publisher.findByName(entry.company)
            if (existingPubisher) {
                newPublisher = existingPubisher
            }
        }

        def newJob = new Job(
                title: entry.title,
                description: entry.description,
                jobUrl: entry.url,
                applyInstructions: entry.how_to_apply,
                active: true,

                publisher: newPublisher,
                type: Type.findByName(entry.type)
        )

        def tagNames = entry.title.split()
        def newTag
        tagNames.each { tagName ->
            try {
                newTag = new Tag(name: tagName)
                newTag.save(failOnError: true)
            } catch (e) {
                log.error("Error in saving tag: ", e)
                def existingTag = Tag.findByName(tagName)
                if (existingTag) {
                    newTag = existingTag
                }
            }
            newJob.addToTags(newTag)
        }

        if (!newJob.save(failOnError: true, validate: true, flush: true)) {
            newJob.errors.allErrors.each {
                log.debug it
            }
        }

        log.info("Job ${newJob.title} imported from publisher ${newPublisher.name}")

        return newJob
    }
}
