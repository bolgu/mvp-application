package app.admin

import grails.core.GrailsApplication
import grails.util.Holders
import wslite.rest.*

class RefreshBoardJob {

    static triggers = {
        //every day at 7:15 AM
        cron name: 'everyMorning', cronExpression: "0 15 7 * * ? *"
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

        def client = new RESTClient(app.admin.RefreshBoardJob.MICROSERVE_URL)
        try {
            def response = client.get(path:'/api/v1.0/jobsImports',
                    query:[
                            location:'europe',
                            active: true,

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
            log.info "Response status ${response.statusCode} and message ${response.json.message}"
        } catch (e) {
            if(e.response?.statusCode) {
                log.info("Error type in REST call: ${e.response?.statusCode} with message ${e.response?.contentAsString}")
            } else {
                log.error("Exception in microservice call: ", e)
            }
        }
    }
}
