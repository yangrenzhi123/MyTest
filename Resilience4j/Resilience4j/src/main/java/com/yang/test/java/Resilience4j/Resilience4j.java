package com.yang.test.java.Resilience4j;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;

public class Resilience4j {

	public static void main(String[] args) {
		CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.ofDefaults();

		CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("my");

		CheckedFunction0<String> decoratedSupplier = CircuitBreaker.decorateCheckedSupplier(circuitBreaker, () -> "Hello");

		
		Try<String> result = Try.of(decoratedSupplier);
		System.out.println(result.isSuccess());
		System.out.println(result.get());
	}
}