package com.codingmogul.microservices.userservice.domain;

import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    String getToken(String subject);
}
