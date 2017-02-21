package app.admin

import app.admin.jobsboard.Job
import app.admin.jobsboard.Publisher
import app.admin.jobsboard.Tag
import app.admin.jobsboard.Type
import grails.transaction.Transactional

@Transactional
class StatisticsService {

    def getTopPublishers() {
        Job.list().countBy { it.publisher }
    }

    def getTopTypes() {
        Job.list().countBy { it.type }
    }

    def getTopTags() {
        Job.list().countBy { it.tags }
    }
}
