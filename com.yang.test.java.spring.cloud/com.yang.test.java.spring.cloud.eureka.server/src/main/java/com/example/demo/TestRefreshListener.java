/*package com.example.demo;

import org.springframework.cloud.bus.event.RefreshListener;
import org.springframework.cloud.bus.event.RefreshRemoteApplicationEvent;
import org.springframework.cloud.context.refresh.ContextRefresher;

public class TestRefreshListener extends RefreshListener {

	public TestRefreshListener(ContextRefresher contextRefresher) {
		super(contextRefresher);
	}

	@Override
	public void onApplicationEvent(RefreshRemoteApplicationEvent event) {
		System.out.println(1);
		
		super.onApplicationEvent(event);
		System.out.println(2);
	}
}*/