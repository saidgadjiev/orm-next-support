package ru.saidgadjiev.ormnext.support.dialect;

import ru.saidgadjiev.ormnext.core.dialect.BaseDialect;

/**
 * SqlLite database dialect.
 *
 * @author said gadjiev
 */
public class SqlLiteDialect extends BaseDialect {

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
