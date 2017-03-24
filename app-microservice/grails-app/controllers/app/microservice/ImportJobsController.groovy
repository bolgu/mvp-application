package app.microservice

import grails.converters.JSON
import javax.annotation.PostConstruct

class ImportJobsController  {

    def importJobsService

    @PostConstruct
    void init() {
        on("importStarted") { data ->
            log.info "Import started at ${new Date()}! for location ${data.location}"
            importJobsService.importJobs()
        }
    }

    def startJobsImport() {
        def location = params.location
        def data = [ip: request.getRemoteAddr(), location: location]
          notify "importStarted", data

        return render(status: 200, contentType: "application/json", [message: "Started"] as JSON)
    }

    def importDeltaJobs() {
        def time = params.time
        if(time) {
            def data = importJobsService.deltaJobs(new Date(new Long(time)))
            return render(status: 200, contentType: "application/json",
                    [message: "Import", jobs: data.jobs, publishers: data.publishers, jobsPublisher: data.jobs.collect {job -> [job.id, job.publisher.name]}] as JSON)
        } else {
            render( status: 400, contentType: "application/json",
                    [code: "ERR_001", message: "Invalid parameters"] as JSON)
        }


    }
}
