package com.yang.test.java.hibernate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.hibernate.cfg.Environment;
import org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl;
import org.hibernate.engine.jdbc.connections.spi.AbstractMultiTenantConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

public class ConfigurableMultiTenantConnectionProvider extends AbstractMultiTenantConnectionProvider {

	public ConfigurableMultiTenantConnectionProvider() {
	    registerConnectionProvider("test");
	    //registerConnectionProvider("test2");
	}
	
	private final Map<String, ConnectionProvider> connectionProviderMap = new HashMap<String, ConnectionProvider>();

	@Override
	protected ConnectionProvider getAnyConnectionProvider() {
		return connectionProviderMap.values().iterator().next();
	}

	@Override
	protected ConnectionProvider selectConnectionProvider(String tenantIdentifier) {
		return connectionProviderMap.get(tenantIdentifier);
	}
	

	@Override
	public Connection getConnection(String tenantIdentifier) throws SQLException {
		return connectionProviderMap.get(tenantIdentifier).getConnection();
	}

	protected void registerConnectionProvider(String tenantIdentifier) {
	    DriverManagerConnectionProviderImpl connectionProvider = new DriverManagerConnectionProviderImpl();
	    
	    Properties properties = new Properties();
	    properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
	    properties.put(Environment.URL, "jdbc:mysql://172.28.51.33:3306/"+tenantIdentifier);
	    properties.put(Environment.USER, "root");
	    properties.put(Environment.PASS, "123456");
	    properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");


	    
	    connectionProvider.configure(properties);
	    connectionProviderMap.put(tenantIdentifier, connectionProvider);
	}
}