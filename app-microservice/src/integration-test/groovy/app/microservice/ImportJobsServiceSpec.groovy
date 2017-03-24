package app.microservice

import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import spock.lang.Specification

@Integration
@Rollback
class ImportJobsServiceSpec extends Specification {

    def importJobsService

    def importResponse

    def setup() {
    }

    def cleanup() {
    }

    void "test GitHub Jobs import"() {

        when:"import the jobs from GitHub"
            importResponse = importJobsService.startJobsImport()
        then:"the import query was succesefull executed"
            importResponse.statusCode == 200
        and:"jobs counter is greater than 1"
            importResponse.jobsCounter >= 1
        and:"we saved all jobs"
            importResponse.savedJobs == importResponse.jobsCounter
    }
}
