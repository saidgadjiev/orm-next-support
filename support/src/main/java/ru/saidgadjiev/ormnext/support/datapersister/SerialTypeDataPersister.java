package ru.saidgadjiev.ormnext.support.datapersister;

import ru.saidgadjiev.ormnext.core.field.datapersister.IntegerDataPersister;

/**
 * Persister for PostgreSql SERIAL TYPE.
 *
 * @author said gadjiev
 */
public class SerialTypeDataPersister extends IntegerDataPersister {

    /**
     * Data type number.
     */
    public static final int SERIAL = 12;

    @Override
    public int getDataType() {
        return SERIAL;
    }
}
