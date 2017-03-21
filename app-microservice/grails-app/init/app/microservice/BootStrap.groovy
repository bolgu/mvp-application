package app.microservice

class BootStrap {

    def init = { servletContext ->
        createCustomerConfiguration()
    }

    def destroy = {
    }

    def createCustomerConfiguration() {
        def customerConfig = new CustomerConfig(
            customerId: "43356",
            apiKey: "AKIAIOSFODNN7EXAMPLE",
            apiSecret: "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY",
            nonce: new Date().time.toString()
        )
        customerConfig.save(flush: true, failOnError: true)
    }
}
