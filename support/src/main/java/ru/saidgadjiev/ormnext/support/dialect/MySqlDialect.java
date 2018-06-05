package ru.saidgadjiev.ormnext.support.dialect;


import ru.saidgadjiev.ormnext.core.dialect.BaseDialect;

/**
 * MySql database dialect.
 *
 * @author said gadjiev
 */
public class MySqlDialect extends BaseDialect {

    @Override
    public String getPrimaryKeyDefinition(boolean generated) {
        StringBuilder builder = new StringBuilder();

        if (generated) {
            builder.append(" AUTO_INCREMENT");
        }
        builder.append(" PRIMARY KEY");

        return builder.toString();
    }

    @Override
    public String getNoArgsInsertDefinition() {
        return "() VALUES ()";
    }
}
