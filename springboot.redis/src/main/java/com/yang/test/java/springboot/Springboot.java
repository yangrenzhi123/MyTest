package com.yang.test.java.springboot;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePool;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.JedisPoolConfig;

@SpringBootApplication(exclude = { RedisAutoConfiguration.class,RedisRepositoriesAutoConfiguration.class })
public class Springboot {

	@Value("${spring.redis.default.type}")
	private int springRedisType;
	@Value("${spring.redis.default.host:127.0.0.1}")
	private String springRedisHost;
	@Value("${spring.redis.default.port:6379}")
	private Integer springRedisPort;
	@Value("${spring.redis.default.cluster.nodes: disabled}")
	private String springRedisClusterNodes;

	@Value("${spring.redis.two.type}")
	private int springRedisTwoType;
	@Value("${spring.redis.two.host:127.0.0.1}")
	private String springRedisTwoHost;
	@Value("${spring.redis.two.port:6379}")
	private Integer springRedisTwoPort;

    @Bean
    public GenericObjectPoolConfig genericObjectPoolConfig() {
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxIdle(3);
        genericObjectPoolConfig.setMinIdle(3);
        genericObjectPoolConfig.setMaxTotal(20);
        genericObjectPoolConfig.setMaxWaitMillis(10000);
        return genericObjectPoolConfig;
    }
	
//	@Bean(value = "defaultLettuceConnectionFactory")
//	public LettuceConnectionFactory defaultLettuceConnectionFactory() {
//		if(springRedisType == 0) {
//			return null;
//		}else if(springRedisType == 1) {
//			return null;
//		}else if(springRedisType == 2) {
//			return null;
//		}else {
//			throw new RuntimeException("type is null");
//		}
//	}
	@Bean(value = "defaultRedisConnectionFactory")
	public JedisConnectionFactory jedisConnectionFactory() {
		if(springRedisType == 0) {
			//JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
			//jedisPoolConfig.setMaxIdle(3);
			//jedisPoolConfig.setMaxTotal(20);
			//return new JedisConnectionFactory(jedisPoolConfig);
			
			JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().build();
			RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
			redisStandaloneConfiguration.setHostName(springRedisHost);
			redisStandaloneConfiguration.setDatabase(0);
			//redisStandaloneConfiguration.setPassword(RedisPassword.of("123456"));
			redisStandaloneConfiguration.setPort(springRedisPort);
			return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
		}else if(springRedisType == 1) {
			return null;
		}else if(springRedisType == 2) {
			if(springRedisClusterNodes.equals("disabled")) {
				throw new RuntimeException("spring.redis.cluster.nodes is null");
			}
			RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
			String[] ipAndPortArray = springRedisClusterNodes.split(",");
			for(String ipAndPort : ipAndPortArray) {
				String[] ipOrPortArray = ipAndPort.split(":");
				redisClusterConfiguration.addClusterNode(new RedisNode(ipOrPortArray[0], Integer.parseInt(ipOrPortArray[1])));
			}
			return new JedisConnectionFactory(redisClusterConfiguration);
		}else {
			throw new RuntimeException("type is null");
		}
	}
	@Bean(value = "defaultRedisTemplate")
	public RedisTemplate<String, Object> defaultRedisTemplate(JedisConnectionFactory defaultRedisConnectionFactory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(defaultRedisConnectionFactory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
		return template;
	}

	@Bean(value = "twoRedisConnectionFactory")
	public JedisConnectionFactory twoRedisConnectionFactory() {
		if(springRedisTwoType == 0) {
			RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
			redisStandaloneConfiguration.setHostName(springRedisTwoHost);
			redisStandaloneConfiguration.setDatabase(0);
			// redisStandaloneConfiguration.setPassword(RedisPassword.of("123456"));
			redisStandaloneConfiguration.setPort(springRedisTwoPort);
			return new JedisConnectionFactory(redisStandaloneConfiguration);
		}else if(springRedisTwoType == 1) {
			return null;
		}else if(springRedisTwoType == 2) {
			if(springRedisClusterNodes.equals("disabled")) {
				throw new RuntimeException("spring.redis.cluster.nodes is null");
			}
			RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
			String[] ipAndPortArray = springRedisClusterNodes.split(",");
			for(String ipAndPort : ipAndPortArray) {
				String[] ipOrPortArray = ipAndPort.split(":");
				redisClusterConfiguration.addClusterNode(new RedisNode(ipOrPortArray[0], Integer.parseInt(ipOrPortArray[1])));
			}
			return new JedisConnectionFactory(redisClusterConfiguration);
		}else {
			throw new RuntimeException("type is null");
		}
	}
	@Bean(value = "twoRedisTemplate")
	public RedisTemplate<String, Object> twoRedisTemplate(JedisConnectionFactory twoRedisConnectionFactory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(twoRedisConnectionFactory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
		return template;
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(Springboot.class, args);
	}
}

@RestController
class TestController {

	@Resource
	RedisTemplate<String, Object> defaultRedisTemplate;
	@Resource
	RedisTemplate<String, Object> twoRedisTemplate;

	@RequestMapping("/")
	public void index() throws Exception {
		Object o = defaultRedisTemplate.opsForValue().get("testKey");
		System.out.println("testKey：" + o);
		
		o = twoRedisTemplate.opsForValue().get("testKey");
		System.out.println("testKey：" + o);
	}
}