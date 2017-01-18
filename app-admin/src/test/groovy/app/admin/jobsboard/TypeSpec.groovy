package app.admin.jobsboard

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Type)
class TypeSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "completed job type creation"() {
        given: "a complete new type"
            def type = new Type(name: "Full-time", description: "Full time job")
        expect:"we can save a complete job type"
            true == type.validate()
    }

    void "can't save a job type without a name"() {
        given: "a job type type without a name"
            def type = new Type(name: "", description: "Full time job")
        expect:"we can't save the job type"
            false == type.validate()
    }

    void "can save a job type without a description"() {
        given: "a job type  without a description"
            def type = new Type(name: "Full-time", description: null)
        expect:"we can save the job type"
            true == type.validate()
    }

}
