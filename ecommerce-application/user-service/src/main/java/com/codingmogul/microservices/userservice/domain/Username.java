package com.codingmogul.microservices.userservice.domain;

import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@RequiredArgsConstructor(staticName = "from")
class Username {
    @Column(name="username")
    private final String value;

    public Username() {
        value = "";
    }
}
