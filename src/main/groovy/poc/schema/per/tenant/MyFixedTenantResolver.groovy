package poc.schema.per.tenant

import org.grails.datastore.mapping.multitenancy.resolvers.FixedTenantResolver

class MyFixedTenantResolver extends FixedTenantResolver {

    @Override
    Serializable resolveTenantIdentifier() {
        return "amazon"
    }
}
