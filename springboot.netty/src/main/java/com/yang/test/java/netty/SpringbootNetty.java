package com.yang.test.java.netty;

import java.net.InetSocketAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.yang.test.java.netty.server.NettyServer;

@SpringBootApplication
public class SpringbootNetty {

	@Autowired
	NettyServer netty;

	@Bean
	public boolean startNetty() {
		netty.run(new InetSocketAddress("127.0.0.1", 3113));
		return true;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootNetty.class, args);
	}
}