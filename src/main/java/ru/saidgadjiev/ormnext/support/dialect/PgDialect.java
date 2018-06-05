package ru.saidgadjiev.ormnext.support.dialect;

import ru.saidgadjiev.ormnext.core.dialect.BaseDialect;
import ru.saidgadjiev.ormnext.core.query.visitor.element.AttributeDefinition;
import ru.saidgadjiev.ormnext.support.datapersister.SerialTypeDataPersister;

/**
 * PostgreSql database dialect.
 *
 * @author said gadjiev
 */
public class PgDialect extends BaseDialect {

    @Override
    public String getPrimaryKeyDefinition(boolean generated) {
        return " PRIMARY KEY";
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
    public String getTypeSqlPresent(AttributeDefinition attributeDefinition) {
        if (attributeDefinition.getDataType() == SerialTypeDataPersister.SERIAL) {
            return "SERIAL";
        }

        return super.getTypeSqlPresent(attributeDefinition);
    }
}
