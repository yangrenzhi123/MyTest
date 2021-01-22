package com.yang.test.java.springcloud.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class RequestTimeFilter implements GlobalFilter, Ordered {

	private static final String REQUEST_TIME_BEGIN = "requestTimeBegin";
	
	@Override
	public int getOrder() {
		return 0;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		System.out.println("in filter ...");

		exchange.getAttributes().put(REQUEST_TIME_BEGIN, System.currentTimeMillis());
		return chain.filter(exchange).then(Mono.fromRunnable(() -> {
			int httpCode = exchange.getResponse().getStatusCode().value();
			System.out.println("httpCode：" + httpCode);
			
			
			Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
			if (startTime != null) {
				System.out.println(exchange.getRequest().getURI().getRawPath() + ": " + (System.currentTimeMillis() - startTime) + "ms");
			}
		}));
	}
}