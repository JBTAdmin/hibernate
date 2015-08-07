package hibernate_example.multitenancy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.engine.jdbc.connections.spi.AbstractMultiTenantConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

/**
 * @author GHajba
 *
 */
public class MultiTenantConnectionProviderImpl extends AbstractMultiTenantConnectionProvider {

    private static final long serialVersionUID = 730657320773645224L;

    private final HashMap<String, ConnectionProvider> connProviderMap = new HashMap<>();

    public MultiTenantConnectionProviderImpl() {

        final List<String> providerNames = new ArrayList<String>();
        providerNames.add("EXAMPLE");
        providerNames.add("db1");
        providerNames.add("db2");

        for (final String providerName : providerNames) {
            this.connProviderMap.put(providerName, new ConnectionProviderImpl(providerName));
        }
    }

    @Override
    protected ConnectionProvider getAnyConnectionProvider() {
        return this.connProviderMap.get(CurrentTenantIdentifierResolverImpl.DEFAULT_TENANT_ID);
    }

    @Override
    protected ConnectionProvider selectConnectionProvider(String tenantId) {
        ConnectionProvider connectionProvider = this.connProviderMap.get(tenantId);
        if (connectionProvider == null) {
            connectionProvider = new ConnectionProviderImpl(CurrentTenantIdentifierResolverImpl.DEFAULT_TENANT_ID);
        }

        return connectionProvider;
    }
}
