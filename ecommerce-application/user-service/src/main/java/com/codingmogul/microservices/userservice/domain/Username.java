package com.codingmogul.microservices.userservice.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Username {
    private String username;

    Username(String username) {
        if(validate(username)){
            this.username = username;
        }
    }

    private boolean validate(String username) {
        return false;
    }
}
