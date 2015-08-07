package hibernate_example.multitenancy;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.spi.Stoppable;

/**
 * @author GHajba
 *
 */
public class MultiTenantConnectionProviderWithDbPoolImpl implements MultiTenantConnectionProvider, Stoppable {

    private static final long serialVersionUID = -6803988674759144554L;

    private final ConnectionProvider connectionProvider = new ConnectionProviderImpl("default");

    @Override
    public boolean isUnwrappableAs(Class arg0) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> arg0) {
        return null;
    }

    @Override
    public Connection getAnyConnection() throws SQLException {
        return this.connectionProvider.getConnection();
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        this.connectionProvider.closeConnection(connection);
    }

    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        final Connection connection = getAnyConnection();
        try {
            connection.createStatement().execute("USE " + tenantIdentifier);
        } catch (final SQLException e) {
            throw new HibernateException("MultiTenantConnectionProvider::Could not alter JDBC connection to specified schema ["
                    + tenantIdentifier + "]", e);
        }
        return connection;
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        try {
            connection.createStatement().execute("USE " + CurrentTenantIdentifierResolverImpl.DEFAULT_TENANT_ID);
        } catch (final SQLException e) {
            throw new HibernateException("Could not alter JDBC connection to specified schema [" + tenantIdentifier + "]", e);
        }
        this.connectionProvider.closeConnection(connection);
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

    @Override
    public void stop() {
    }

}
