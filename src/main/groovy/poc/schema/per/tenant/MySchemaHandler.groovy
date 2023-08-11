package poc.schema.per.tenant

import groovy.util.logging.Slf4j
import org.grails.datastore.gorm.jdbc.schema.DefaultSchemaHandler

import javax.sql.DataSource

@Slf4j
class MySchemaHandler extends DefaultSchemaHandler {

    private List<String> validTenants = ['amazon', 'empik']

    MySchemaHandler() {
        super("SET SCHEMA '%s'", "CREATE SCHEMA '%s'", "public")
    }

    @Override
    Collection<String> resolveSchemaNames(DataSource dataSource) {
        log.info("resolveSchemaNames()")

        List existingSchemas = SchemaUtils.getExistingSchemas(dataSource)

        log.info "existingSchemas = ${existingSchemas}"

        return validTenants
    }

}
