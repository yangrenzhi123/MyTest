package com.example.demo;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Component;
import io.micrometer.core.instrument.MeterRegistry;

@Component
public class GaugeBean {

	private final AtomicInteger gauge;

	public GaugeBean(MeterRegistry registry) {
		// 注册指标
		this.gauge = registry.gauge("test_y_gn_beat_gauge", new AtomicInteger(0));
	}

	public void handleMessage(int value) {
		this.gauge.set(value);
	}
}