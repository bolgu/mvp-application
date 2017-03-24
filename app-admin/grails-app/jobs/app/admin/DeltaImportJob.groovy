package app.admin

import app.admin.jobsboard.Job
import app.admin.jobsboard.Publisher
import grails.core.GrailsApplication
import org.grails.web.json.JSONObject
import wslite.rest.ContentType
import wslite.rest.RESTClient
import wslite.rest.RESTClientException

class DeltaImportJob {

    def utilityService

    static triggers = {
        //every day at 7:15 AM
        cron name: 'everyMorning', cronExpression: "0 15 8 * * ? *"
        //second, minute, hour, day of month, month, day of week, year
    }

    GrailsApplication grailsApplication

    def securityService
    public static final String MICROSERVE_URL = "https://localhost:8444"

    def execute() {

        def configuration = grailsApplication.config.grails.microservice.customer.config
        def customerId = configuration.customerId
        def apiKey = configuration.apiKey
        def apiSecret = configuration.apiSecret
        def nonce = new Date().time.toString()
        def message = nonce + customerId + apiKey
        def signature = securityService.hmacSha256(apiSecret, message)

        def client = new RESTClient(DeltaImportJob.MICROSERVE_URL)
        try {
            def response = client.get(path:'/api/v1.0/jobsDeltas',
                    query:[
                            time: new Date().time,

                            nonce: nonce,
                            apiKey: apiKey,
                            signature: signature
                    ],
                    accept: ContentType.JSON,
                    connectTimeout: 5000,
                    readTimeout: 10000,
                    followRedirects: true,
                    useCaches: false,
                    sslTrustAllCerts: true
            )
            log.info "Response status ${response.statusCode} and message ${response.json.message}; jobs to be imported ${response.json.jobs.size()}"

            convertAndImportInRepository(response)

            log.info "\nImport ended"

        } catch (RESTClientException e) {
            if(e?.response?.statusCode) {
                log.info("Error type in REST call: ${e.response?.statusCode} with message ${e.response?.contentAsString}")
            }
        } catch (Exception e) {
            log.error("Exception in microservice call: ", e)
        }
    }

    private void convertAndImportInRepository(response) {

        // convert and save publishers
        def publishers = response.json.publishers
        def publishersList = [:]
        for (JSONObject publisher : publishers) {

            publishersList[publisher.id] = publisher?.name

            def newPublisher = new Publisher()
            publisher.keys().each { key ->
                if (publisher.get(key) && key != "dateCreated" && key != "lastUpdated") {
                    newPublisher[key] = publisher.get(key)
                }
            }
            newPublisher.validate()
            if (newPublisher.hasErrors()) {
                newPublisher = Publisher.findByName(publisher.name)
            } else {
                def logo = newPublisher.logoUrl ? utilityService.uriToImage(newPublisher.logoUrl) : null
                newPublisher.logo = logo
                newPublisher.save(flush: true)
            }

        }

        // convert and save jobs
        def jobs = response.json.jobs
        for (JSONObject job : jobs) {
            def newJob = new Job()
            newJob.publisher = Publisher.findByName(publishersList[job.publisher.id])
            job.keys().each { key ->
                if (job.get(key) && key != "dateCreated" && key != "lastUpdated" &&
                    key != "type" && key != "publisher" && key != "tags") {
                    newJob[key] = job.get(key)
                }
            }

            // save tags for this job
//            def tagNames = newJob.title.split()
//            def newTag
//            tagNames.each { tagName ->
//                try {
//                    newTag = new Tag(name: tagName)
//                    newTag.save(failOnError: true)
//                } catch (e) {
//                    newTag.errors.allErrors.each {
//                        log.debug it.toString()
//                    }
//                    def existingTag = Tag.findByName(tagName)
//                    if (existingTag) {
//                        newTag = existingTag
//                    }
//                }
//                newJob.addToTags(newTag)
//            }

            newJob.save(failOnError: true)
        }
    }
}
