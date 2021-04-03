package com.codingmogul.microservices.userservice.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Setter(AccessLevel.PRIVATE)
@Getter
@RequiredArgsConstructor
public class User {
    @EmbeddedId
    private UserNo userNo;

    @Embedded
    private final Username username;

    @Embedded
    private final Password password;

    @Embedded
    private final Email email;

    public User() {
        this.username = null;
        this.password = null;
        this.email = null;
    }

    public static User create(String username, String password, String email) {
        return new User(new Username(username), new Password(password), new Email(email));
    }
}

