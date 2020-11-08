package com.yang.test.java.springboot.webflux;

import java.util.stream.IntStream;

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
		Flux<String> result = Flux.fromStream(IntStream.range(0, 1).mapToObj(i -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "flux-"+i;
		}));
		return result;
	}
}