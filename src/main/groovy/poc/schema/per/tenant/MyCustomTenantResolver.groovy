package poc.schema.per.tenant

import org.grails.datastore.mapping.multitenancy.AllTenantsResolver

class MyCustomTenantResolver implements AllTenantsResolver {

    @Override
    Serializable resolveTenantIdentifier() {
        return "amazon"
    }

    @Override
    Iterable<Serializable> resolveTenantIds() {
        return ['amazon', 'empik']
    }
}
