package poc.schema.per.tenant

import grails.converters.JSON
import grails.gorm.multitenancy.CurrentTenant
import grails.gorm.multitenancy.Tenants
import grails.gorm.transactions.Transactional

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
        render(bookService.getBooks() as JSON)
    }


    @CurrentTenant
    @Transactional
    def create() {
        log.info "current tenant = ${Tenants.currentId()}"
        render(bookService.createBook() as JSON)
    }

    def initAllTenants() {
        Tenants.eachTenant { String tenantId ->
            bookService.createBook("initEachTenants - ${tenantId}")
        }
        render([] as JSON)
    }

    def initSpecificTenants() {
        Tenants.withId("amazon") { String tenantId ->
            bookService.createBook("initSpecificTenants - ${tenantId}")
        }
        Tenants.withId("empik") { String tenantId ->
            bookService.createBook("initSpecificTenants - ${tenantId}")
        }
        render([] as JSON)
    }

}
