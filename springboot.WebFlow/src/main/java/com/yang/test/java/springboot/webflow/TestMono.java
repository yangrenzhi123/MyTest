package com.yang.test.java.springboot.webflow;

import java.util.function.BiConsumer;

import reactor.core.publisher.Mono;

public class TestMono {

	public static void main(String[] args) throws InterruptedException {
//		Mono.delay(Duration.ofSeconds(10)).subscribe(n -> {
//			System.out.println(123);
//		});
		

		Mono<String> m = Mono.fromSupplier(() -> "Hello1");
		new Thread(new Runnable() {
			public void run() {
				m.subscribe(System.out::println);
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				m.subscribe(System.out::println);
			}
		}).start();
		m.then();
		
//		Mono.justOrEmpty(Optional.of("Hello2")).subscribe(System.out::println);
//		Mono.create(sink -> sink.success("Hello3")).subscribe(System.out::println);

		//Flux 相当于一个 RxJava Observable，能够发出 0~N 个数据项，然后（可选地）completing 或 erroring。处理多个数据项作为stream。
//		Flux.just("Hello", "World").subscribe(System.out::println);
//		Flux.fromArray(new Integer[] { 1, 2, 3 }).subscribe(System.out::println);
//		Flux.empty().subscribe(System.out::println);
//		Flux.range(1, 10).subscribe(System.out::println);
//		Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);
		
		System.out.println(1);
		Thread.sleep(50000L);
	}
}