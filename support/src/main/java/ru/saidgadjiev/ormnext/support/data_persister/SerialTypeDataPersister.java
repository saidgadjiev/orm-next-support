package ru.saidgadjiev.ormnext.support.data_persister;

import ru.saidgadjiev.ormnext.core.field.data_persister.IntegerDataPersister;

public class SerialTypeDataPersister extends IntegerDataPersister {

    public static final int SERIAL = 12;

    @Override
    public int getDataType() {
        return SERIAL;
    }
}
