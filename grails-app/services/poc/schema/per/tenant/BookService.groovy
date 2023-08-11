package poc.schema.per.tenant

import grails.gorm.multitenancy.CurrentTenant
import grails.gorm.transactions.Transactional

@CurrentTenant
class BookService {


    List getUsers() {
        return Book.list()
    }

    @Transactional
    Book createUser() {
        return new Book(title: 'aaaa').save(flush: true)
    }

}
