package app.microservice

import grails.artefact.Interceptor
import grails.converters.JSON


class SecurityInterceptor {

    int order = Interceptor.HIGHEST_PRECEDENCE

    public static final String API_URI = "/api/v1.0/**"
    def securityService

    SecurityInterceptor() {
        match(uri: API_URI) // using strings
    }

    boolean before() {

        def nonce = params.nonce
        def apiKey = params.apiKey
        def signature = params.signature

        // Parameters were not posted in API request
        if(!nonce || !apiKey || !signature) {
            render( status: 400, contentType: "application/json",
                    [code: "ERR_001", message: "Missing key, signature and nonce parameters"] as JSON)
            return false
        }

        def customerConfig = CustomerConfig.findByApiKey(apiKey)

        // Can't find customer with selected API key
        if(!customerConfig) {
            render( status: 403, contentType: "application/json",
                    [code: "ERR_002", message: "Authentication failed"] as JSON)
            return false
        }

        def customerId = customerConfig.customerId

        def lastNonce = customerConfig.nonce
        // Check your nonce value. It must be greater than last nonce used
        if(nonce <= lastNonce) {
            render( status: 401, contentType: "application/json",
                    [code: "ERR_003", message: "Invalid nonce"] as JSON)
            return false
        }

        def message = nonce + customerId + apiKey
        def testSignature = securityService.hmacSha256(customerConfig.apiSecret, message)

        if(signature == testSignature) {
            customerConfig.nonce = nonce
            customerConfig.save(flush: true)
            return true

        } else {
            // Posted signature doesn't match with ours
            render( status: 401, contentType: "application/json",
                    [code: "ERR_004", message: "Invalid signature"] as JSON)
            return false
        }
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
