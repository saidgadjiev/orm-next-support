package ru.saidgadjiev.ormnext.support.datapersister;

import ru.saidgadjiev.ormnext.core.dialect.BaseDialect;
import ru.saidgadjiev.ormnext.core.field.SqlType;
import ru.saidgadjiev.ormnext.core.field.datapersister.IntegerDataPersister;
import ru.saidgadjiev.ormnext.core.query.visitor.element.AttributeDefinition;

/**
 * Persister for PostgreSql SERIAL TYPE.
 *
 * @author Said Gadjiev
 */
public class SerialTypeDataPersister extends IntegerDataPersister {

    @Override
    public SqlType getOrmNextSqlType() {
        return SqlType.OTHER;
    }

    @Override
    public String getOtherTypeSql(BaseDialect baseDialect, AttributeDefinition def) {
        return "SERIAL";
    }
}
