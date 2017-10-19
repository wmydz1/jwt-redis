package com.logoocc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logoocc.jwt.utils.JwtTokenUtil;
import com.logoocc.redis.tools.RedisUtils;

@Controller
public class IndexController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private RedisUtils redisUtil;

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		// String uuid = jwtTokenUtil.uuIdGenerate();
		// String test_create = jwtTokenUtil.doGenerateToken("samchen", uuid);
		// if (jwtTokenUtil.validateToken(test_create, "samchen", uuid)) {
		// System.out.println("auth pass");
		// } else {
		// System.out.println("auth false");
		// }
		return "index.html";
	}

	@RequestMapping("/key")
	public ResponseEntity<Object> authkey(Map<String, Object> model) {
		String username = "samchen";
		String uuid = jwtTokenUtil.uuIdGenerate();
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		String test_create = jwtTokenUtil.doGenerateToken(username, uuid);
		tokenMap.put("access_token", test_create);
		Map<String, String> encodemap = new HashMap<String, String>();
		encodemap.put("username", username);
		encodemap.put("uuid", uuid);
		String token = "";
		try {
			token = mapper.writeValueAsString(tokenMap);
			String encodestr = mapper.writeValueAsString(encodemap);
			redisUtil.saveToken(test_create, encodestr);
		} catch (Exception e) {
			token = "error";
		}
		return new ResponseEntity<Object>(token, HttpStatus.OK);
	}

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ResponseEntity<Object> auth(Map<String, Object> model,
			@RequestParam(value = "token", required = true) String token) {
		String endcodestr = "auth false";
		if (jwtTokenUtil.validateRedisToken(token)) {
			endcodestr = "auth pass";
		}
		return new ResponseEntity<Object>(endcodestr, HttpStatus.OK);
	}

	@RequestMapping(value = "/refresh", method = RequestMethod.POST)
	public ResponseEntity<Object> refresh(Map<String, Object> model,
			@RequestParam(value = "token", required = true) String token) {
		// replace endcodestr to your token by /key
		String result = "refresh false";
		if (redisUtil.getToken(token) != null) {
			redisUtil.refreashToken(token);
			result = "refresh ok";
		}
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}

}
