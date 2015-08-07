package hibernate_example.multitenancy;

import java.sql.Connection;
import java.sql.SQLException;

//import org.apache.commons.dbcp2.BasicDataSource;
import org.h2.jdbcx.JdbcDataSource;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

/**
 * @author GHajba
 *
 */
public class ConnectionProviderImpl implements ConnectionProvider {

    private static final long serialVersionUID = 1697402608114701057L;

    private final JdbcDataSource basicDataSource = new JdbcDataSource();

    public ConnectionProviderImpl(String database) {

        // these should come from a property file
        this.basicDataSource.setUrl("jdbc:h2:file:./example;DB_CLOSE_DELAY=-1;MVCC=TRUE");
        this.basicDataSource.setUser("sa");
        this.basicDataSource.setPassword("");
    }

    @Override
    public boolean isUnwrappableAs(Class arg0) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> arg0) {
        return null;
    }

    @Override
    public void closeConnection(Connection arg0) throws SQLException {
        arg0.close();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return this.basicDataSource.getConnection();
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

}
