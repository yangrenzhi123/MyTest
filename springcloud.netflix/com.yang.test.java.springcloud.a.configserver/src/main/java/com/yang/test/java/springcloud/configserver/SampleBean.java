package com.yang.test.java.springcloud.configserver;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
public class SampleBean {
	private final Counter counter;

	public SampleBean(MeterRegistry registry) {
		this.counter = registry.counter("test_y_gn_beat_counter");
	}

	public void handleMessage() {
		this.counter.increment();
	}
}