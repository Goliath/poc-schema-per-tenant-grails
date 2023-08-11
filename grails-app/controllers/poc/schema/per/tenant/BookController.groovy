package poc.schema.per.tenant

import grails.converters.JSON
import grails.gorm.multitenancy.CurrentTenant
import grails.gorm.multitenancy.Tenants
import grails.gorm.transactions.Transactional

import java.sql.Connection
import java.sql.ResultSet

class BookController {

    BookService bookService
    def dataSource

    static defaultAction = "list"



    @CurrentTenant
    def list() {
        log.info "current tenant = ${Tenants.currentId()}"
        def results = Book.list()
        render(results as JSON)
    }

    @CurrentTenant
    def schema() {
        log.info "current tenant = ${Tenants.currentId()}"
        render(SchemaUtils.getExistingSchemas(dataSource) as JSON)
    }

    def listWithService() {
        render(bookService.getUsers() as JSON)
    }


    @CurrentTenant
    @Transactional
    def create() {
        log.info "current tenant = ${Tenants.currentId()}"
        render(bookService.createUser() as JSON)
    }

}
