package com.codingmogul.microservices.userservice.infrastructure.validator;


public class RequestFieldValidateException extends Exception{

    RequestFieldValidateException(String fieldName) {
        super(fieldName);
    }
}
