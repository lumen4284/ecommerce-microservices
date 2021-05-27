package com.codingmogul.microservices.userservice.infrastructure.auth;

import com.codingmogul.microservices.userservice.domain.AuthService;
import com.codingmogul.microservices.userservice.infrastructure.exception.ExternalServiceException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {
    private RestTemplate restTemplate;

    public AuthServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getToken(String subject) {
        Map<String, String> request = new HashMap<>();
        request.put("subject", subject);
        try{
            Map result = restTemplate.postForObject("http://localhost:8000/auth/token", request, Map.class);    System.out.println(result.get("token"));
            return (String) result.get("token");
        }catch(Exception e){
            throw new ExternalServiceException(getClass().getSimpleName(), e.getMessage());
        }
    }
}
