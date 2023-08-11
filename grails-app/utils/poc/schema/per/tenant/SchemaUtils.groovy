package poc.schema.per.tenant

import java.sql.Connection
import java.sql.ResultSet

class SchemaUtils {

    static List getExistingSchemas(def dataSource) {
        List existingSchemas = []
        try (Connection connection = dataSource.getConnection()) {
            ResultSet schemas = connection.getMetaData().getSchemas()
            while(schemas.next()) {
                existingSchemas << schemas.getString("TABLE_SCHEM")
            }
        }
        return existingSchemas
    }
}
