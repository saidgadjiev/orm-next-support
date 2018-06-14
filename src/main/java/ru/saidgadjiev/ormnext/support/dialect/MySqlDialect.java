package ru.saidgadjiev.ormnext.support.dialect;


import ru.saidgadjiev.ormnext.core.dialect.BaseDialect;
import ru.saidgadjiev.ormnext.core.query.visitor.element.AttributeDefinition;

/**
 * MySql database dialect.
 *
 * @author Said Gadjiev
 */
public class MySqlDialect extends BaseDialect {

    @Override
    public String getDatabaseName() {
        return "mysql";
    }

    @Override
    public String getPrimaryKeyDefinition(AttributeDefinition attributeDefinition) {
        StringBuilder builder = new StringBuilder();

        builder.append(getTypeSql(attributeDefinition));
        if (attributeDefinition.isGenerated()) {
            builder.append(" AUTO_INCREMENT");
        }
        builder.append(" PRIMARY KEY");

        return builder.toString();
    }

    @Override
    public String getNoArgsInsertDefinition() {
        return "() VALUES ()";
    }

    @Override
    public String getGeneratedDefinition(AttributeDefinition attributeDefinition) {
        return getTypeSql(attributeDefinition) + " AUTO_INCREMENT";
    }
}
