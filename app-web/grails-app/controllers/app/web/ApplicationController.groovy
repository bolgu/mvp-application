package app.web

import app.admin.security.Role
import app.admin.security.User
import app.admin.security.UserRole
import grails.converters.JSON
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

    def register() {

        def username = request.JSON.username
        def password = request.JSON.password

        if(!username || !password) {
            return render(status: 400, text: 'Please provide user and password for signup')
        }

        def user = new User(username: username, password: password)
        user.validate()
        if(user.hasErrors()) {
            def message = ""
            user.errors.allErrors.each {
                    log.info it.toString()
                    message += g.message(error: it)
            }
            return render(status: 400, text: message)
        } else {
            user.save(flush: true)
            def roleUser = Role.findByAuthority('ROLE_CUSTOMER')
            def userRole = new UserRole(user: user, role: roleUser)
            userRole.save(flush: true, failOnError: false)
            return render(status: 200, text: [message: 'Your account was created. Please login!'] as JSON)
        }
    }
}
