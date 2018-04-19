package com.example.demo;

import org.springframework.cloud.bus.event.RefreshListener;
import org.springframework.cloud.context.refresh.ContextRefresher;

public class TestRefreshListener extends RefreshListener {

	public TestRefreshListener(ContextRefresher contextRefresher) {
		super(contextRefresher);
	}
}