package ru.saidgadjiev.ormnext.support.connection.source;

import ru.saidgadjiev.ormnext.core.connection.DatabaseConnection;
import ru.saidgadjiev.ormnext.core.connection.DatabaseConnectionImpl;
import ru.saidgadjiev.ormnext.core.connection.source.ConnectionSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Connection source which retrieve connections from connection pool.
 *
 * @author said gadjiev
 */
public class PolledConnectionSource implements ConnectionSource<Connection> {

    /**
     * Data source.
     */
    private DataSource dataSource;

    /**
     * Available connections.
     */
    private Set<DatabaseConnection<Connection>> available = new HashSet<>();

    /**
     * In use connections.
     */
    private Set<DatabaseConnection<Connection>> inUse = new HashSet<>();

    /**
     * Create a new instance.
     *
     * @param dataSource target data source.
     */
    public PolledConnectionSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public synchronized DatabaseConnection<Connection> getConnection() throws SQLException {
        if (available.isEmpty()) {
            available.add(createNew());
        }

        DatabaseConnection<Connection> connection = available.iterator().next();

        available.remove(connection);
        inUse.add(connection);

        return connection;
    }

    @Override
    public synchronized void releaseConnection(DatabaseConnection<Connection> connection) throws SQLException {
        inUse.remove(connection);
        available.add(connection);
    }

    /**
     * Create a new database connection.
     *
     * @return a new connection
     * @throws SQLException any SQL exceptions
     */
    private DatabaseConnection<Connection> createNew() throws SQLException {
        return new DatabaseConnectionImpl(dataSource.getConnection());
    }

    @Override
    public void close() throws SQLException {
        for (DatabaseConnection<Connection> connection : inUse) {
            connection.close();
        }
        for (DatabaseConnection<Connection> connection : available) {
            connection.close();
        }
    }

    @Override
    public String toString() {
        return String.format("Pool available=%d inUse=%d", available.size(), inUse.size());
    }
}
