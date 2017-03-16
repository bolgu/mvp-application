package app.admin

import wslite.rest.*

class RefreshBoardJob {
    static triggers = {
        //every day at 7:15 AM
        cron name: 'everyMorning', cronExpression: "0 15 7 * * ? *"
        //second, minute, hour, day of month, month, day of week, year
    }
    public static final String MICROSERVE_URL = "https://localhost:8090"

    def execute() {
        // execute job

        def client = new RESTClient(app.admin.RefreshBoardJob.MICROSERVE_URL)
        def response = client.get(path:'/list',
                                query:[type:'remote', include_entities:true],
                                connectTimeout: 5000,
                                readTimeout: 10000,
                                followRedirects: false,
                                useCaches: false,
                                sslTrustAllCerts: true)

        log.info "Response status: " + response.statusCode + " : " + response.json.state
    }
}
