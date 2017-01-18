package app.admin.jobsboard

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Publisher)
class PublisherSpec extends Specification {

    def publisher

    def setup() {

        publisher = new Publisher(
                name: "Github",
                url: "http://jobs.github.com",
                contactEmail: "admin@github.com",
                location: "USA",
                twitterId: "twitter",
                logo: []
        )
    }

    def cleanup() {
    }

    void "completed publisher creation"() {
        given:"a new job publisher is created with all the data"
        expect:"a new job publisher is created with all the data"
            true == publisher.validate()
    }

    void "can't create job publisher without name"() {
        given:"a new job publisher is created without name"
            publisher.name = ""
        expect:"validations are failing"
            false == publisher.validate()
    }

    void "can't create job publisher without a valid email"() {
        given:"a new job publisher is created without title"
            publisher.contactEmail = "x-gmail.com"
        expect:"validations are failing"
            false == publisher.validate()
    }
}
