package com.yang.test.java.hibernate;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class TenantIdResolver implements CurrentTenantIdentifierResolver {

	public String resolveCurrentTenantIdentifier() {
		return "test";
	}

	public boolean validateExistingCurrentSessions() {
		return false;
	}
}