package com.codingmogul.microservices.userservice.infrastructure.exception;

public class ExternalServiceException extends RuntimeException{
    public ExternalServiceException(String serviceName, String message) {
        super(String.format("[%s] -> %s", serviceName, message));

    }
}
