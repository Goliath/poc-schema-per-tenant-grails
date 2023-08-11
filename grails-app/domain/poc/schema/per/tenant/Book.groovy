package poc.schema.per.tenant

import grails.gorm.MultiTenant;

class Book implements MultiTenant<Book> {
    String title
}
