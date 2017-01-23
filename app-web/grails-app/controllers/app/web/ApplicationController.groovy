package app.web

import grails.core.GrailsApplication
import grails.plugin.springsecurity.annotation.Secured
import grails.util.Environment
import grails.plugins.*

import java.security.Security

@Secured(["permitAll"])
class ApplicationController implements PluginManagerAware {

    GrailsApplication grailsApplication
    GrailsPluginManager pluginManager

    def index() {
        [grailsApplication: grailsApplication, pluginManager: pluginManager]
    }
}
