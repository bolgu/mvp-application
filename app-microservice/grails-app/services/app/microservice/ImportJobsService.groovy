package app.microservice

import app.admin.jobsboard.Job
import app.admin.jobsboard.Publisher
import app.admin.jobsboard.Tag
import app.admin.jobsboard.Type
import grails.transaction.Transactional
import wslite.rest.RESTClient

@Transactional
class ImportJobsService {

    def utilityService
    static transactional = false

    public static final String GITHUB_JOBS_URL = "https://jobs.github.com"

    def importEuropeanJobsFromGitHub() {

        def savedJobs = []

        def client = new RESTClient(GITHUB_JOBS_URL)
        def response = client.get(path:'//positions.json?location=europe',
                query:[type:'remote', include_entities:true],
                connectTimeout: 5000,
                readTimeout: 10000,
                followRedirects: false,
                useCaches: false,
                sslTrustAllCerts: true)
        log.info "Response status: " + response.statusCode

        response.json.each { entry ->

            def newJob = insertJobInRepository(entry)
            if(newJob.id) {
                savedJobs.add(newJob)
            }
        }

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
                    println it
                }
            }
        } catch (e) {
            // just if the publisher exists in database (unique constraint in broken) then use the existing publisher for the current job
            def existingPubisher = Publisher.findByName(entry.company)
            if (existingPubisher) {
                newPublisher = existingPubisher
            }
        }

        def tagNames = entry.title.split()
        def tags = []
        tagNames.each { tagName ->
            tags.add(new Tag(name: tagName))
        }

        def newJob = new Job(
                title: entry.title,
                description: entry.description,
                jobUrl: entry.url,
                applyInstructions: entry.how_to_apply,
                active: true,

                publisher: newPublisher,
                type: Type.findByName(entry.type),
                tags: tags
        )

        if (!newJob.save(failOnError: true, validate: true, flush: true)) {
            newJob.errors.allErrors.each {
                println it
            }
        }

        log.info("Job ${newJob.title} imported from publisher ${newPublisher.name}")

        return newJob
    }
}
