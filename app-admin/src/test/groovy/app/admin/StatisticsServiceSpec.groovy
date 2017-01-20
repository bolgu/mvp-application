package app.admin

import app.admin.jobsboard.Job
import app.admin.jobsboard.Publisher
import app.admin.jobsboard.Tag
import app.admin.jobsboard.Type
import grails.buildtestdata.mixin.Build
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(StatisticsService)
@Mock([Job, Tag])
@Build([Job, Tag, Type, Publisher])
class StatisticsServiceSpec extends Specification {

    def setup() {

    }

    def cleanup() {
    }

    void "get top publishers when we don't have nothing in our system"() {
        given: "when we don't have any job published"

        when: "we get top publishers"
            def publishers = service.getTopPublishers()
        then:"we will see 0 publishers"
            publishers.size() == 0
    }

    void "get top publishers when we have multiple jobs published by the same publisher"() {
        given: "when we have one 2 jobs published by the same publisher"
            def tag = Tag.build()
            def type = Type.build()
            def publisher = Publisher.build()
            Job.build(publisher: publisher, type: type, tags: [tag])
            Job.build(publisher: publisher, type: type, tags: [tag])

        when: "we get top publishers"
            def publishers = service.getTopPublishers()
            def pair = publishers.find { key, value -> key.name.equals(publisher.name) }
        then:"we will see 2 publishers"
            publishers.size() == 1
            pair?.value == 2
    }

    void "get top publishers when we have multiple jobs published by the multiple publishers"() {
        given: "when we have one 3 jobs published by 2 different publishers"
            def tag = Tag.build()
            def type = Type.build()
            def publisher1 = Publisher.build(name: 'p1')
            def publisher2 = Publisher.build(name: 'p2')

            Job.build(publisher: publisher1, type: type, tags: [tag])
            Job.build(publisher: publisher2, type: type, tags: [tag])
            Job.build(publisher: publisher2, type: type, tags: [tag])

        when: "we get top publishers"
            def publishers = service.getTopPublishers()
            def pair1 = publishers.find { key, value -> key.name.equals(publisher1.name) }
            def pair2 = publishers.find { key, value -> key.name.equals(publisher2.name) }
        then:"we will see 2 publishers"
            publishers.size() == 2
            pair1?.value == 1
            pair2?.value == 2
    }
}
