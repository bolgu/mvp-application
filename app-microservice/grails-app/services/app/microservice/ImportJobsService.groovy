package app.microservice

import grails.transaction.Transactional
import reactor.bus.Event
import reactor.spring.context.annotation.Consumer
import reactor.spring.context.annotation.Selector

@Transactional
class ImportJobsService {

    def importGitHubJobsService

    def importJobs() {
        importGitHubJobsService.importEuropeanJobsFromGitHub()
    }
}
