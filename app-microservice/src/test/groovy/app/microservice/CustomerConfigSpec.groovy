package app.microservice

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(CustomerConfig)
class CustomerConfigSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test customer saving"() {
        given: "a valid customer"
            def customerConfig = new CustomerConfig(
                    customerId: "43356",
                    apiKey: "AKIAIOSFODNN7EXAMPLE",
                    apiSecret: "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY",
                    nonce: new Date().time.toString()
            )
        when: "the customer is saved"
            customerConfig.save(flush: true, failOnError: true)
        then: "the customer is in the list of saved customers"
            CustomerConfig.first().customerId == "43356"
    }

    void "test customer saving without id"() {
        given: "a invalid customer without id"
            def customerConfig = new CustomerConfig(
                    apiKey: "AKIAIOSFODNN7EXAMPLE",
                    apiSecret: "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY",
                    nonce: new Date().time.toString()
            )
        when: "the customer is saved"
            customerConfig.save(flush: true, failOnError: false)
        then: "the customer is in the list of saved customers"
            CustomerConfig.first() == null
    }
}
