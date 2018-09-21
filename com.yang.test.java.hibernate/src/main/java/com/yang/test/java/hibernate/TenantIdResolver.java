package com.yang.test.java.hibernate;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class TenantIdResolver implements CurrentTenantIdentifierResolver {

	public String resolveCurrentTenantIdentifier() {
		// 正常情况，需要从线程变量获取数据
		
		return "test";
	}

	public boolean validateExistingCurrentSessions() {
		return false;
	}
}