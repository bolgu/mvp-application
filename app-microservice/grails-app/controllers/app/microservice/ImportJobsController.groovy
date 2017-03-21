package app.microservice

import grails.converters.JSON
import javax.annotation.PostConstruct

class ImportJobsController  {
    static responseFormats = ['json', 'xml']

    def importJobsService
    def securityService

    @PostConstruct
    void init() {
        on("importStarted") { data ->
            log.info "Import started at ${new Date()}! for location ${data.location}"
            importJobsService.importJobs()
        }
    }

    def importJobs() {
        def location = params.location
        def data = [ip: request.getRemoteAddr(), location: location]
        //  notify "importStarted", data

        return render(status: 200, contentType: "application/json", [message: "Started"] as JSON)
    }
}
