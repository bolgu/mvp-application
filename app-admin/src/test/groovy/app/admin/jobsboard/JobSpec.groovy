package app.admin.jobsboard

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Job)
class JobSpec extends Specification {

    def job

    def setup() {
        job = new Job(
                title: "Software Developer",
                description: "Build a stock position monitoring system to support our existing trading engines. This will consist of a server and also client APIs in C and Java.",
                jobUrl: "https://stackoverflow.com/jobs/132418/software-developer-silver-fern-investments",
                contactEmail: "admin@stackoverflow.com",
                applyInstructions: "admin@stackoverflow.com",
                salaryEstimate: "1000000 per year",
                active: true,
                remote: true,
                expirationDate: new Date() + 30,
        )
    }

    def cleanup() {
    }

    void "completed job creation"() {
        given:"a new job is created with all the data"
        expect:"the validation is passing"
        true == job.validate()
    }

    void "can't create job without title"() {
        given:"a new job is created without title"
            job.title = ""
        expect:"validations are failing"
            false == job.validate()
    }

    void "can't create job without a valid email"() {
        given:"a new job is created without title"
        job.contactEmail = "x-gmail.com"
        expect:"validations are failing"
        false == job.validate()
    }
}
