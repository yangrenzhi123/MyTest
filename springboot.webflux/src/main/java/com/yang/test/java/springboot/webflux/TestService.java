package com.yang.test.java.springboot.webflux;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

@Service
public class TestService {

	public static ExecutorService p = Executors.newFixedThreadPool(1000);
	
	public CompletableFuture<String> getList() {
		return CompletableFuture.supplyAsync(() -> {
			return "success";
		}, p);
	}
}