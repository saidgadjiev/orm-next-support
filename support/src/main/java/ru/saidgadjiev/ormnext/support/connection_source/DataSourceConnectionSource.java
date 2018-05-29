package ru.saidgadjiev.ormnext.support.connection_source;

import ru.saidgadjiev.ormnext.core.connection_source.ConnectionSource;
import ru.saidgadjiev.ormnext.core.connection_source.DatabaseConnection;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceConnectionSource implements ConnectionSource<Connection> {

    private DataSource dataSource;

    public DataSourceConnectionSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DatabaseConnection<Connection> getConnection() throws SQLException {
        return new DatabaseConnectionImpl(dataSource.getConnection());
    }
}
