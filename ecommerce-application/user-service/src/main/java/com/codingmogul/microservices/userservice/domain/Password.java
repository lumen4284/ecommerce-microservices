package com.codingmogul.microservices.userservice.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Getter
@RequiredArgsConstructor(staticName = "from")
class Password {
    @NotNull
    @Column(name="password")
    private final String value;

    public Password() {
        value = "";
    }
}
