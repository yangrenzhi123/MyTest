package com.yang.test.java.springcloud.configserver;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

@Component
public class SampleBean {
	private final Counter counter;
	private final AtomicInteger gauge;
	private final DistributionSummary summary;
	private final Timer timer;
	Random r = new Random();

	public SampleBean(MeterRegistry registry) {
		// 注册指标
		this.counter = registry.counter("test_y_gn_beat_counter");
		this.gauge = registry.gauge("test_y_gn_beat_gauge", new AtomicInteger(0));
		this.summary = registry.summary("test_y_gn_beat_summary");
		this.timer = Timer.builder("timer").tag("timer","timer").description("timer").register(registry);
	}

	// 执行指标计算，本处为方法调用一次指标累加一次
	public void handleMessage() {
		this.counter.increment();
		this.gauge.decrementAndGet();
		this.summary.record(r.nextInt(100));
        this.timer.record(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException e){
            }
        });
	}
}