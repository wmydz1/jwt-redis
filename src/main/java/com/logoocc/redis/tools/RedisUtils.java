package com.logoocc.redis.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logoocc.config.JedisConfig;

import redis.clients.jedis.Jedis;

@Service
public class RedisUtils {

	@Autowired
	JedisConfig jedisConfig;

	public static final int EXPIRETIME = 600; // sec

	public void saveToken(String token, String encodejson) {
		Jedis jedis = jedisConfig.getJedisPoolInstance().getResource();
		jedis.setex(token, EXPIRETIME, encodejson);// 604800
		jedis.close();
	}

	public String getToken(String token) {
		Jedis jedis = jedisConfig.getJedisPoolInstance().getResource();
		// System.out.println("get |token time is " + jedis.ttl(token));
		// System.out.println("--> " + jedis.get(token));
		String encodestr = jedis.get(token);
		jedis.close();
		return encodestr;
	}

	public void refreashToken(String token) {
		Jedis jedis = jedisConfig.getJedisPoolInstance().getResource();
		if (jedis.ttl(token) < 500) {
			jedis.expire(token, EXPIRETIME);// 604800
		}
		jedis.close();
		// System.out.println("refeash |token time is " + jedis.ttl(token));
	}

}
