package ru.saidgadjiev.ormnext.support.dialect;

import ru.saidgadjiev.ormnext.core.dialect.BaseDialect;
import ru.saidgadjiev.ormnext.core.query.visitor.element.AttributeDefinition;

/**
 * SqlLite database dialect.
 *
 * @author Said Gadjiev
 */
public class SqlLiteDialect extends BaseDialect {

    @Override
    public String getDatabaseName() {
        return "sqlite";
    }

    @Override
    public String getPrimaryKeyDefinition(AttributeDefinition attributeDefinition) {
        StringBuilder builder = new StringBuilder();

        builder.append(getTypeSql(attributeDefinition)).append(" PRIMARY KEY");
        if (attributeDefinition.isGenerated()) {
            builder.append(" AUTOINCREMENT");
        }

        return builder.toString();
    }

    @Override
    public String getNoArgsInsertDefinition() {
        return "DEFAULT VALUES";
    }

    @Override
    public String getGeneratedDefinition(AttributeDefinition attributeDefinition) {
        return getTypeSql(attributeDefinition) + " AUTOINCREMENT";
    }
}
