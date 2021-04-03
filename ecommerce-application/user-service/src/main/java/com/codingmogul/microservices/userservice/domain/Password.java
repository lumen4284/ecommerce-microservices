package com.codingmogul.microservices.userservice.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Password {
    private String password;

    Password(String password) {
        if(validate(password))
        this.password = password;
    }

    private boolean validate(String password) {
        return false;
    }
}
