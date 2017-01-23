package app.web

import app.admin.jobsboard.Job
import app.admin.jobsboard.Publisher
import app.admin.jobsboard.Tag
import app.admin.jobsboard.Type
import app.admin.security.Role
import app.admin.security.User
import app.admin.security.UserRole

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

class BootStrap {

    def init = { servletContext ->

        populateUsers()
        populateTypes()
        populateDefaultTags()

        populateTestPublishers()
        populateTestJobs()
    }

    def populateUsers() {
        def adminRole = new Role(authority: 'ROLE_ADMIN').save()
        def operatorRole = new Role(authority: 'ROLE_OPERATOR').save()
        def customerRole = new Role(authority: 'ROLE_CUSTOMER').save()

        def adminUser = new User(username: 'admin', password: 'password').save()
        def operatorUser = new User(username: 'operator', password: 'password').save()
        def customerUser = new User(username: 'customer', password: 'password').save()

        UserRole.create adminUser, adminRole
        UserRole.create operatorUser, operatorRole
        UserRole.create customerUser, customerRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }

        assert User.count() == 3
        assert Role.count() == 3
        assert UserRole.count() == 3
    }

    def populateTypes() {
        if(Type.count() == 0) {
            def types = ['Commission', 'Volunteer', 'Part-time', 'Internship', 'Full-time',
                         'Temporary', 'Apprenticeship', 'Contract', 'Permanent']

            types.each {
                def type = new Type(name: it, description: "${it.replace('-', ' ')} job")
                type.save(flash: true, failOnError: true)
            }
        }
    }

    def populateDefaultTags() {
        if(Tag.count() == 0) {
            def defaultTags = ['Mobile', 'Engineer', 'Dev', 'Remote', 'Senior']
            defaultTags.each {
                def tag = new Tag(name: it)
                tag.save(flash: true, failOnError: true)
                log.info "tag ${tag.id} saved \n"
            }
        }
    }

    def populateTestPublishers() {
        if(Publisher.count() == 0) {
            def logo = uriToImage("https://assets-cdn.github.com/images/modules/logos_page/GitHub-Mark.png")
            def publisher = new Publisher(
                    name: "Github",
                    url: "http://jobs.github.com",
                    contactEmail: "admin@github.com",
                    location: "USA",
                    twitterId: "twitter",
                    logo: logo?:[]
            )
            publisher.save(flash: true, failOnError: true)

            logo = uriToImage("https://cdn.sstatic.net/Sites/stackoverflow/company/img/logos/se/se-icon.png")
            publisher = new Publisher(
                    name: "Stackoverflow",
                    url: "http://stackoverflow.com/jobs",
                    contactEmail: "admin@stackoverflow.com",
                    location: "USA",
                    twitterId: "stackoverflow",
                    logo: logo?:[]
            )
            publisher.save(flash: true, failOnError: true)
        }
    }

    def populateTestJobs() {
        if(Job.count() == 0) {

            def tags = []
            tags << Tag.first()
            tags << Tag.last()

            def job = new Job(
                    title: "Software Developer",
                    description: "Build a stock position monitoring system to support our existing trading engines. This will consist of a server and also client APIs in C and Java.",
                    jobUrl: "https://stackoverflow.com/jobs/132418/software-developer-silver-fern-investments",
                    contactEmail: "admin@stackoverflow.com",
                    applyInstructions: "admin@stackoverflow.com",
                    salaryEstimate: "1000000 per year",
                    type: Type.last(),
                    publisher: Publisher.last(),
                    active: true,
                    remote: true,
                    expirationDate: new Date() + 30,
                    tags: tags
            )
            job.save(flash: true, failOnError: true)
        }
    }

    def uriToImage(String uri) {
        try {
            URL url = new URL(uri);

            // read image direct from url
            BufferedImage image = ImageIO.read(url);

            // write image to outputstream
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            baos.flush();

            // get bytes
            byte[] imageBytes = baos?.toByteArray();
            return imageBytes
        } catch (Exception e) {
            log.debug("Getting image form url not possible: ${e.message}")
            return []
        }
    }

    def destroy = {
    }
}
