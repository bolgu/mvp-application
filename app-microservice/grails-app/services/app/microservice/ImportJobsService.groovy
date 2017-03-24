package app.microservice

import app.admin.jobsboard.Job
import app.admin.jobsboard.Publisher
import grails.transaction.Transactional

@Transactional
class ImportJobsService {

    def importGitHubJobsService

    def importJobs() {
        importGitHubJobsService.importEuropeanJobsFromGitHub()
    }

    def deltaJobs(Date time) {
        return [jobs: Job.findAllByDateCreatedBetween(time - 1, time),
                publishers: Publisher.findAllByDateCreatedBetween(time - 1, time)]
    }
}
