package com.logoocc.jwt.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logoocc.redis.tools.RedisUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -3693166263513837659L;

	// Key key = MacProvider.generateKey();
	final String key = "logoocc.com";

	@Autowired
	private RedisUtils redisUtil;

	public Boolean validateToken(String token, String subjectFromServer, String uuidFromServer) {
		final String subjectFromToken = getSubjectFromToken(token);
		final String uuidFromToken = getUUIDFromToken(token);
		try {
			Jwts.parser().setSigningKey(key).parseClaimsJws(token);
			// OK, we can trust this JWT
			return (subjectFromToken.equals(subjectFromServer) && uuidFromToken.equals(uuidFromServer));
		} catch (Exception e) {
			// don't trust the JWT!
			return false;
		}
	}

	public Boolean validateTokenNoRedis(String token) {
		try {
			Jwts.parser().setSigningKey(key).parseClaimsJws(token);
			// OK, we can trust this JWT
			return true;
		} catch (Exception e) {
			// don't trust the JWT!
			return false;
		}
	}

	/**
	 * @param subject:用户名
	 * @param uuid:UUID           
	 * @return
	 */
	public String doGenerateToken(String subject, String uuid) {
		return Jwts.builder().setSubject(subject).setId(uuid).signWith(SignatureAlgorithm.HS512, key).compact();
	}

	public String getSubjectFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public String getUUIDFromToken(String token) {
		return getClaimFromToken(token, Claims::getId);
	}

	public Object getClaimItemFromToken(String token, Object claimName) {
		final Claims claims = getAllClaimsFromToken(token);
		return claims.get(claimName);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
	}

	public String uuIdGenerate() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public boolean validateRedisToken(String accessToken) {

		String objectJson = redisUtil.getToken(accessToken);
		boolean validateResult = false;
		try {
			if (objectJson != null) {
				ObjectMapper mapper = new ObjectMapper();
				TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
				};
				HashMap<String, Object> infoObject = mapper.readValue(objectJson, typeRef);
				if (infoObject.get("username") != null && infoObject.get("uuid") != null) {
					if (validateToken(accessToken, infoObject.get("username").toString(),
							infoObject.get("uuid").toString())) {
						// auth pass
						validateResult = true;
					}
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return validateResult;

	}

}
