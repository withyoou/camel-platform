package com.withyou.platform.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author admin
 * @Date 2019-10-08 14:21
 **/
@Component
public class PreAuthProvider implements AuthenticationManager {

    private String tokenSign;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getPrincipal();
        Claims claims = Jwts.parser().setSigningKey(tokenSign.getBytes()).parseClaimsJws(token).getBody();
        String id = claims.getId();
        List<GrantedAuthority> roles = new ArrayList<>();
        return new PreAuthenticatedAuthenticationToken(id, null, roles);
    }
}
