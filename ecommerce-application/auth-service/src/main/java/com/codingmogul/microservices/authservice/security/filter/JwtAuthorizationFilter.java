package com.codingmogul.microservices.authservice.security.filter;

import com.codingmogul.microservices.authservice.security.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private static final String HEADER_STRING = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    private JwtTokenUtil jwtTokenUtil;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        super(authenticationManager);
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String subject = Optional.ofNullable(request.getHeader(HEADER_STRING))
                .map(e -> jwtTokenUtil.getSubjectFromToken(e.replace(TOKEN_PREFIX, "")))
                .orElseThrow(IllegalArgumentException::new);

        return new UsernamePasswordAuthenticationToken(subject, null, new ArrayList<>());
    }
}
