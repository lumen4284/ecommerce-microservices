package com.codingmogul.microservices.userservice.domain;

import com.codingmogul.microservices.userservice.infrastructure.exception.ExternalServiceException;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    String getToken(String subject);
}
