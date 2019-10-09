package com.withyou.platform.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Author admin
 * @Date 2019-10-08 13:53
 **/
@Component
public class PreAuthFilter extends AbstractPreAuthenticatedProcessingFilter {


    @Autowired
    public PreAuthFilter(PreAuthProvider preAuthProvider) {
        setAuthenticationManager(preAuthProvider);
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest httpServletRequest) {
        String headerToken = httpServletRequest.getHeader("token");
        if (StringUtils.isEmpty(headerToken)) {
            return Arrays.stream(httpServletRequest.getCookies())
                    .filter(c -> "token".equals(c.getName()))
                    .map(Cookie::getValue)
                    .findFirst().orElse(null);
        }
        return headerToken;
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest httpServletRequest) {
        return null;
    }
}
