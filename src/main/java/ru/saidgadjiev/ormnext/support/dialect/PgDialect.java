package ru.saidgadjiev.ormnext.support.dialect;

import ru.saidgadjiev.ormnext.core.dialect.BaseDialect;
import ru.saidgadjiev.ormnext.core.query.visitor.element.AttributeDefinition;

/**
 * PostgreSql database dialect.
 *
 * @author Said Gadjiev
 */
public class PgDialect extends BaseDialect {

    @Override
    public String getDatabaseName() {
        return "postgresql";
    }

    @Override
    public String getPrimaryKeyDefinition(AttributeDefinition attributeDefinition) {
        return " SERIAL PRIMARY KEY";
    }

    @Override
    public String getNoArgsInsertDefinition() {
        return " DEFAULT VALUES";
    }

    @Override
    public String getEntityNameEscape() {
        return "\"";
    }

    @Override
    public String getGeneratedDefinition(AttributeDefinition attributeDefinition) {
        return " SERIAL ";
    }
}
