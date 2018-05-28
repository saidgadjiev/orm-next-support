package ru.saidgadjiev.ormnext.support.database_type;

import ru.saidgadjiev.ormnext.core.database_type.BaseDatabaseType;
import ru.saidgadjiev.ormnext.core.query.visitor.element.AttributeDefinition;
import ru.saidgadjiev.ormnext.support.data_persister.SerialTypeDataPersister;

public class PGDatabaseType extends BaseDatabaseType {
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
