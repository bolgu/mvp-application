package app.microservice

class CustomerConfig {

    String customerId
    String apiKey
    String apiSecret
    String nonce // last nonce

    static constraints = {
    }

    static mapping = {
        cache true
    }
}
