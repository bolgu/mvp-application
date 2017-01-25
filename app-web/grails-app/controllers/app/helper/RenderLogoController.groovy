package app.helper

import app.admin.jobsboard.Publisher
import grails.plugin.springsecurity.annotation.Secured

@Secured(["permitAll"])
class RenderLogoController {

    def index() {

        def id = params.id
        def byte[] publisherLogo = []
        if(id) {
            publisherLogo = Publisher.get(id).logo
        }

        render file: new ByteArrayInputStream(publisherLogo), contentType: 'image/png'
    }
}
