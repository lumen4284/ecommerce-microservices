package com.codingmogul.microservices.userservice.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Email {
    private String email;

    Email(String email) {
        if(validate(email))
        this.email = email;
    }

    private boolean validate(String email) {
        return false;
    }

}
