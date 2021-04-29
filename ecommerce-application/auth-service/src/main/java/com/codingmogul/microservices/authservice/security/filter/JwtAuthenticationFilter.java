package com.codingmogul.microservices.authservice.security.filter;

import com.codingmogul.microservices.authservice.security.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        setFilterProcessesUrl("/auth/token");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            DefaultAuthRequest credentials =
                    new ObjectMapper().readValue(request.getInputStream(), DefaultAuthRequest.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getSubject(), ""));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = jwtTokenUtil.generateToken((String) authResult.getPrincipal());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(new DefaultAuthResponse(token)));
        response.getWriter().flush();
    }

    @Getter
    private static class DefaultAuthResponse {
        private String token;

        DefaultAuthResponse(String token) {
            this.token = token;
        }
    }

    @Getter
    private static class DefaultAuthRequest {
        private String subject;
    }
}
