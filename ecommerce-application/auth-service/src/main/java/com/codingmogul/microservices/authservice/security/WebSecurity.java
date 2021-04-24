package com.codingmogul.microservices.authservice.security;

import com.codingmogul.microservices.authservice.security.filter.JwtAuthenticationFilter;
import com.codingmogul.microservices.authservice.security.filter.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private static final String SIGN_UP_URL = "/api/services/controller/user";
    private JwtTokenUtil jwtTokenUtil;

    public WebSecurity(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests()
//                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtTokenUtil))
//                .addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtTokenUtil))
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
////
        http.csrf().disable().authorizeRequests()
                // 토큰을 활용하는 경우 모든 요청에 대해 접근이 가능하도록 함
                .anyRequest().permitAll()
                .and()
                // 토큰을 활용하면 세션이 필요 없으므로 STATELESS로 설정하여 Session을 사용하지 않는다.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // form 기반의 로그인에 대해 비활성화 한다.
                .formLogin()
                .disable()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtTokenUtil))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtTokenUtil));
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new CustomAuthenticationProvider());
    }


}
