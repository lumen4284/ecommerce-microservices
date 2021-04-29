package com.codingmogul.microservices.userservice.application;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    private RestTemplate restTemplate;

    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    String getToken(String subject){
        Map<String, String> request = new HashMap<>();
        request.put("subject", subject);
        Map result =
                restTemplate.postForObject("http://localhost:8000/auth/token", request, Map.class);

        assert result != null;
        System.out.println(result.get("token"));
        return Strings.EMPTY;
    }

}
