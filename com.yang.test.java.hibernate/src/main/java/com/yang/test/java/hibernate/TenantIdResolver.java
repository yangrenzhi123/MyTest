package com.yang.test.java.hibernate;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class TenantIdResolver implements CurrentTenantIdentifierResolver {

	public String resolveCurrentTenantIdentifier() {
		// �����������Ҫ���̱߳�����ȡ����
		
		return "test";
	}

	public boolean validateExistingCurrentSessions() {
		return false;
	}
}