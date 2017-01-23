package app.admin.jobsboard

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TypeController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Type.list(params), model:[typeCount: Type.count()]
    }

    def show(Type type) {
        respond type
    }

    @Transactional
    def save(Type type) {
        if (type == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (type.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond type.errors, view:'create'
            return
        }

        type.save flush:true

        respond type, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Type type) {
        if (type == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (type.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond type.errors, view:'edit'
            return
        }

        type.save flush:true

        respond type, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Type type) {

        if (type == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        type.delete flush:true

        render status: NO_CONTENT
    }
}
