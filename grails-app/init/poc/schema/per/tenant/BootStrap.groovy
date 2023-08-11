package g532.multitenant

import grails.gorm.multitenancy.Tenants
import poc.schema.per.tenant.Book

class BootStrap {

    def init = { servletContext ->

        Tenants.withId("aa") {
            if (Book.count() == 0) {
                (1..10).each {
                    Book user = new Book(userid: it)
                    user.save()
                }
            }
        }

    }
    def destroy = {
    }
}
