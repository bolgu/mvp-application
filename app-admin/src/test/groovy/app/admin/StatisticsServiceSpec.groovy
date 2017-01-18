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

    void "get the total count of jobs, publishers, tags and types on empty system"() {
        given: "when we don't have nothing in our system"

        when: "we don't have jobs in system"
        then: "we will get 0 jobs in system"
            service.getCount("jobs") == 0

        when: "we don't have publishers in system"
        then: "we will get 0 publishers in system"
            service.getCount("publishers") == 0

        when: "we don't have tags in system"
        then: "we will get 0 tags in system"
            service.getCount("tags") == 0

        when: "we don't have types in system"
        then: "we will get 0 types in system"
            service.getCount("types") == 0
    }

    void "get the total count of jobs, publishers, tags and types on operational system"() {
        given: "when we don't have nothing in our system"

        when: "we have 2 tags in system"
            Tag.build()
            Tag.build()
        then:"we will get 0 tags in system"
            service.getCount("tags") == 2

        when: "we have 1 type in system"
            Type.build()
        then:"we will get 1 type in system"
            service.getCount("types") == 1

        when: "we don't have 1 publisher in system"
            Publisher.build()
            Publisher.build()
        then:"we will get 1 publisher in system"
            service.getCount("publishers") == 2

        when: "we have 2 jobs in system"
            Job.build(publisher: Publisher.last(), type: Type.last(), tags: [Tag.last()])
            Job.build(publisher: Publisher.last(), type: Type.last(), tags: [Tag.last()])
        then:"we will get 2 jobs in system"
            service.getCount("jobs") == 2
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
