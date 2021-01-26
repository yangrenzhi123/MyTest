package com.yang.test.java.springboot.webflux;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*https://zhuanlan.zhihu.com/p/91477669*/
@SpringBootApplication
public class WebfluxStartup {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxStartup.class, args);
	}
}

@RestController
class HelloController {

	@Autowired
	TestService testService;
	
	@GetMapping("/1")
	public Mono<String> hello() throws InterruptedException {
        return Mono.create(stringMonoSink -> testService.getList().thenApply(s -> {
            //填写成功结果
            stringMonoSink.success(s);
            return s;
        }));
	}

	@GetMapping("/2")
	public Flux<String> hello2() {
		IntStream is = IntStream.range(0, 10).parallel();

		Stream<String> stream = is.mapToObj(i -> {
			return "flux-" + i;
		});

		Flux<String> result = Flux.fromStream(stream);
		return result;
	}
}