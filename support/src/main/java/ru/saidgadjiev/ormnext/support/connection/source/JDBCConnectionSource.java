package ru.saidgadjiev.ormnext.support.connection.source;

import ru.saidgadjiev.ormnext.core.connection.DatabaseConnection;
import ru.saidgadjiev.ormnext.core.connection.DatabaseConnectionImpl;
import ru.saidgadjiev.ormnext.core.connection.source.ConnectionSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connection source which retrieve connections from {@link DriverManager}.
 *
 * @author said gadjiev
 */
public class JDBCConnectionSource implements ConnectionSource<Connection> {

    /**
     * Full database url with username, password.
     */
    private final String dataBaseUrl;

    /**
     * Create a new instance.
     *
     * @param dataBaseUrl     target database url
     * @param driverClassName target driver class name
     * @throws SQLException exceptions when load driver
     */
    public JDBCConnectionSource(String dataBaseUrl, String driverClassName) throws SQLException {
        this.dataBaseUrl = dataBaseUrl;
        initialize(driverClassName);
    }

    /**
     * Initialize connection source.
     *
     * @param driverClassName target driver class name
     * @throws SQLException exceptions when load driver
     */
    private void initialize(String driverClassName) throws SQLException {
        loadDriver(driverClassName);
    }

    /**
     * Load database driver.
     *
     * @param driverClassName target driver class name
     * @throws SQLException exceptions when load driver
     */
    private void loadDriver(String driverClassName) throws SQLException {
        if (driverClassName != null) {
            try {
                Class.forName(driverClassName);
            } catch (ClassNotFoundException e) {
                throw new SQLException(
                        "Driver class was not found.  Missing jar with class " + driverClassName + ".", e
                );
            }
        }
    }

    @Override
    public DatabaseConnection<Connection> getConnection() throws SQLException {
        return new DatabaseConnectionImpl(DriverManager.getConnection(dataBaseUrl));
    }

}
