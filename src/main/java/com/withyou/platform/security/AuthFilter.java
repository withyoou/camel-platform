package com.withyou.platform.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.withyou.platform.security.domain.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * @Author admin
 * @Date 2019-10-08 15:56
 **/
@Component
public class AuthFilter extends AbstractAuthenticationProcessingFilter {


    @Autowired
    private AuthProvider authProvider;

    protected AuthFilter() {
        super("/login");
        setAuthenticationManager(new ProviderManager(Collections.singletonList(authProvider)));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        LoginForm loginForm = mapper.readValue(httpServletRequest.getInputStream(), LoginForm.class);
        if (StringUtils.isEmpty(loginForm.getUsername()) || StringUtils.isEmpty(loginForm.getPassword())) {
            throw new BadCredentialsException("Username \\ password could not be empty.");
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword());
        return getAuthenticationManager().authenticate(token);
    }
}
