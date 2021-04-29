package com.codingmogul.microservices.userservice.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@RequiredArgsConstructor(staticName = "from")
public class Email {
    @Column(name = "email")
    private final String value;

    public Email() {
        value = "";
    }
}
