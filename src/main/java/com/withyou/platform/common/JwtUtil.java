package com.withyou.platform.common;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;


/**
 * @Author admin
 * @Date 2019-10-08 15:07
 **/
@Component
public class JwtUtil {

    private String tokenSecret;

    public String createToken(String user) {
        return Jwts.builder()
                .setId(user)
                .setExpiration(DateTime.now().plusHours(2).toDate())
                .signWith(SignatureAlgorithm.HS256, tokenSecret.getBytes())
                .compact();
    }
}
