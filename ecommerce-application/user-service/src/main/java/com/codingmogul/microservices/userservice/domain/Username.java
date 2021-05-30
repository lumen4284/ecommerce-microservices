package com.codingmogul.microservices.userservice.domain;

import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@RequiredArgsConstructor(staticName = "from")
class Username {

    @NotNull
    @Column(name="username")
    private final String value;

    public Username() {
        value = "";
    }
}
