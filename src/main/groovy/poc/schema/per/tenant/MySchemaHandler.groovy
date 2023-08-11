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
//        super("SET SCHEMA '%s'", "CREATE SCHEMA '%s'", "public")
        super("CREATE SCHEMA IF NOT EXISTS %s; SET SEARCH_PATH = %s;", "CREATE SCHEMA %s", "public") // please notice lack of quotation mark in CREATE SCHEMA part
    }

    @Override
    Collection<String> resolveSchemaNames(DataSource dataSource) {
        log.info("resolveSchemaNames()")

        List existingSchemas = SchemaUtils.getExistingSchemas(dataSource)

        log.info "existingSchemas = ${existingSchemas}"

        return validTenants
    }

}
