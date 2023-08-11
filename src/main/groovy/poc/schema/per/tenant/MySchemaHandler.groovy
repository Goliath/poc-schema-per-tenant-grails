package poc.schema.per.tenant

import groovy.util.logging.Slf4j
import org.grails.datastore.gorm.jdbc.schema.DefaultSchemaHandler

import javax.sql.DataSource
import java.sql.Connection

@Slf4j
class MySchemaHandler extends DefaultSchemaHandler {

    private List<String> validTenants = ['amazon', 'empik']

    @Override
    void useSchema(Connection connection, String name) {
        String useStatement = String.format(useSchemaStatement, name, name)
        log.debug("Executing SQL Set Schema Statement: ${useStatement}")
        connection
                .createStatement()
                .execute(useStatement)
    }

    MySchemaHandler() {
        // TODO
        // super("SET SCHEMA '%s'", "CREATE SCHEMA '%s'", "public") => is not working fine on postgres.
        // CREATE IF NOT EXISTS is a workaround for postgres issue with 'SET SCHEMA' not failing when given schema does not exists.
        // HibernateDataStore.addTenantForSchemaInternal relies on useSchema to fail, so it can be created in that case.
        // As this workaround is a simplification, in production code we should rather manually check if the schema exists and act accordingly.
        super("CREATE SCHEMA IF NOT EXISTS %s; SET SEARCH_PATH = %s;", "CREATE SCHEMA %s", "public") // please notice lack of quotation mark in CREATE SCHEMA part
    }

    @Override
    Collection<String> resolveSchemaNames(DataSource dataSource) {
        log.info("resolveSchemaNames()")

        List existingSchemas = SchemaUtils.getExistingSchemas(dataSource)   //TODO not really needed

        log.info "existingSchemas = ${existingSchemas}"

        return validTenants
    }

}
