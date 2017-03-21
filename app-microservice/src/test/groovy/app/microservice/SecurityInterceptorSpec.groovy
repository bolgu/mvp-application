package app.microservice


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(SecurityInterceptor)
class SecurityInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test security interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(uri: "/api/v1.0/jobsImports")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }

    void "Test security interceptor not matching"() {
        when:"A request don't matches the interceptor"
        withRequest(uri: "/apis/v1.0/jobsImports")

        then:"The interceptor does not match"
        !interceptor.doesMatch()
    }
}
