package poc.schema.per.tenant

import grails.gorm.multitenancy.CurrentTenant
import grails.gorm.transactions.Transactional
import org.grails.orm.hibernate.HibernateDatastore

@CurrentTenant
class BookService {

    HibernateDatastore hibernateDatastore

    List getBooks() {
        return Book.list()
    }

    @Transactional
    Book createBook(final String title = "sample title") {
        Book sample = new Book(title: title).save(flush: true)
        return sample
    }

}
