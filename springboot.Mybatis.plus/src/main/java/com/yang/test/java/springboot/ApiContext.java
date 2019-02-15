package com.yang.test.java.springboot;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

@Component
public class ApiContext {
	private static final String KEY_CURRENT_PROVIDER_ID = "KEY_CURRENT_PROVIDER_ID";
	private static final Map<String, Object> mContext = Maps.newConcurrentMap();

	public void setCurrentProviderId(Long providerId) {
		mContext.put(KEY_CURRENT_PROVIDER_ID, providerId);
	}

	public Long getCurrentProviderId() {
		return 1L;//(Long) mContext.get(KEY_CURRENT_PROVIDER_ID);
	}
}