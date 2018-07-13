package com.yang.test.java.hibernate;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl;
import org.hibernate.engine.jdbc.connections.spi.AbstractMultiTenantConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;

public class ConfigurableMultiTenantConnectionProvider extends AbstractMultiTenantConnectionProvider implements MultiTenantConnectionProvider {

	private static final long serialVersionUID = -6910514036999028636L;
	private final Map<String, ConnectionProvider> connectionProviderMap = new HashMap<String, ConnectionProvider>();
	
	public ConfigurableMultiTenantConnectionProvider(Map<String, ConnectionProvider> connectionProviderMap) {
		this.connectionProviderMap.putAll(connectionProviderMap);
	}

	@Override
	protected ConnectionProvider getAnyConnectionProvider() {
		return connectionProviderMap.values().iterator().next();
	}

	@Override
	protected ConnectionProvider selectConnectionProvider(String tenantIdentifier) {
		return connectionProviderMap.get(tenantIdentifier);
	}
	
	private void init() {
	    registerConnectionProvider( "1" );
	    registerConnectionProvider( "2" );
	}
	
	protected void registerConnectionProvider(String tenantIdentifier) {
	    DriverManagerConnectionProviderImpl connectionProvider = new DriverManagerConnectionProviderImpl();
	    connectionProviderMap.put( tenantIdentifier, connectionProvider );
	}
}