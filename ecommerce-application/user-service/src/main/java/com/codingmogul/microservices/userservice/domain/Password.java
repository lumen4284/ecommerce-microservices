package com.codingmogul.microservices.userservice.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@RequiredArgsConstructor(staticName = "from")
class Password {
    private final String value;

    public Password() {
        value = "";
    }
}
