package app.admin

import grails.transaction.Transactional

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import java.security.InvalidKeyException

@Transactional
class SecurityService {

    def hmacSha256(String secretKey, String data) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256")
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256")
            mac.init(secretKeySpec)
            byte[] digest = mac.doFinal(data.getBytes())
            def encodedData = digest.encodeBase64().toString()
            return encodedData
        } catch (InvalidKeyException e) {
            throw new RuntimeException("Invalid key exception while converting to HMac SHA256")
        }
    }
}
