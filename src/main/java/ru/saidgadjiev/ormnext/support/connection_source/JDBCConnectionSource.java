package ru.saidgadjiev.ormnext.support.connection_source;

import ru.saidgadjiev.ormnext.core.connection_source.ConnectionSource;
import ru.saidgadjiev.ormnext.core.connection_source.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectionSource implements ConnectionSource<Connection> {

    private final String dataBaseUrl;

    private String driverClassName;


    public JDBCConnectionSource(String dataBaseUrl, String driverClassName) throws SQLException {
        this.dataBaseUrl = dataBaseUrl;
        this.driverClassName = driverClassName;
        initialize();
    }

    private void initialize() throws SQLException {
        loadDriver();
    }

    private void loadDriver() throws SQLException {
        if (driverClassName != null) {
            try {
                Class.forName(driverClassName);
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver class was not found.  Missing jar with class " + driverClassName + ".", e);
            }
        }
    }

    @Override
    public DatabaseConnection<Connection> getConnection() throws SQLException {
        return new DatabaseConnectionImpl(DriverManager.getConnection(dataBaseUrl));
    }

}
