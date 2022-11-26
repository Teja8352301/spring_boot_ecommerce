package com.teja.springapplication.jwt;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {

	@Value("${jwt.standard.token}")
	private String standardToken;

	@Value("${jwt.split.pattern}")
	private String splitPattern;

	public String jwtGenerator(String email, String password) {
		Key hmacKey = new SecretKeySpec(java.util.Base64.getDecoder().decode(standardToken),
				SignatureAlgorithm.HS256.getJcaName());

		Instant now = Instant.now();
		String jwtToken = Jwts.builder().claim("email", email).claim("password", password)
				.setId(UUID.randomUUID().toString()).setIssuedAt(Date.from(now)).signWith(hmacKey).compact();
		return jwtToken;
	}

	public Boolean jwtVerify(String email, String password, String jwtToken) {
		Key hmacKey = new SecretKeySpec(java.util.Base64.getDecoder().decode(standardToken),
				SignatureAlgorithm.HS256.getJcaName());

		try {
			Jws<Claims> jwt = Jwts.parserBuilder().setSigningKey(hmacKey).build().parseClaimsJws(jwtToken);

			String jwsObject = jwt.toString();

			if (jwsObject.indexOf(password) > -1 && jwsObject.indexOf(email) > -1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public Object getEmailIdByJwtToken(String jwtToken) {
		Key hmacKey = new SecretKeySpec(java.util.Base64.getDecoder().decode(standardToken),
				SignatureAlgorithm.HS256.getJcaName());
		try {
			Jws<Claims> jwt = Jwts.parserBuilder().setSigningKey(hmacKey).build().parseClaimsJws(jwtToken);
			String[] split_string = jwtToken.split("\\.");
			String base64EncodedBody = split_string[1];
			Base64 base64Url = new Base64(true);
			String body = new String(base64Url.decode(base64EncodedBody));
			JSONObject jsonObject = new JSONObject(body);
			String result = jsonObject.get("email").toString() + splitPattern + jsonObject.get("password").toString();
			return result;
		} catch (Exception e) {
			return null;
		}
	}

}
