package com.logoocc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfig {

	@Bean
	public JedisPool getJedisPoolInstance() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(8);
		config.setMaxTotal(18);
		JedisPool pool = new JedisPool(config, "127.0.0.1", 6379, 2000, "123456");
		return pool;
	}
}
