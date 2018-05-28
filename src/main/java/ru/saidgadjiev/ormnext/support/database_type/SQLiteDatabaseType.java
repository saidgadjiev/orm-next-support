package ru.saidgadjiev.ormnext.support.database_type;

import ru.saidgadjiev.ormnext.core.database_type.BaseDatabaseType;

public class SQLiteDatabaseType extends BaseDatabaseType {

    @Override
    public String getPrimaryKeyDefinition(boolean generated) {
        StringBuilder builder = new StringBuilder();

        builder.append(" PRIMARY KEY");
        if (generated) {
            builder.append(" AUTOINCREMENT");
        }

        return builder.toString();
    }

    @Override
    public String getNoArgsInsertDefinition() {
        return "DEFAULT VALUES";
    }
}
