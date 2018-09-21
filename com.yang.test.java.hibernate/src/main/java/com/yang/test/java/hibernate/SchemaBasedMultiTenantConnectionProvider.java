package com.yang.test.java.hibernate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.spi.Configurable;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;
import org.hibernate.service.spi.Stoppable;

@SuppressWarnings("rawtypes")
public class SchemaBasedMultiTenantConnectionProvider implements MultiTenantConnectionProvider, Stoppable, Configurable, ServiceRegistryAwareService{

	private static final long serialVersionUID = 8839221169213760346L;
	private final DriverManagerConnectionProviderImpl connectionProvider = new DriverManagerConnectionProviderImpl();
	
	public boolean isUnwrappableAs(Class unwrapType) {
		return this.connectionProvider.isUnwrappableAs(unwrapType);
	}

	public <T> T unwrap(Class<T> unwrapType) {
		return this.connectionProvider.unwrap(unwrapType);
	}

	public Connection getAnyConnection() throws SQLException {
		final Connection connection = connectionProvider.getConnection();
		return connection;
	}

	public void releaseAnyConnection(Connection connection) throws SQLException {
		connectionProvider.closeConnection(connection);
	}

	public Connection getConnection(String tenantIdentifier) throws SQLException {
		final Connection connection = connectionProvider.getConnection();
		
		connection.createStatement().execute("USE " + tenantIdentifier);
		
		return connection;
	}

	public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
	}

	public boolean supportsAggressiveRelease() {
		return this.connectionProvider.supportsAggressiveRelease();
	}

	public void injectServices(ServiceRegistryImplementor serviceRegistry) {
		this.connectionProvider.injectServices(serviceRegistry);
	}

	public void configure(Map configurationValues) {
		this.connectionProvider.configure(configurationValues);
	}

	public void stop() {
		this.connectionProvider.stop();
	}
}