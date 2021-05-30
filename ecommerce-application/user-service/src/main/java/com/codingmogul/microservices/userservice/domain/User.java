package com.codingmogul.microservices.userservice.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;
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
        return new User(Username.from(username), Password.from(password), Email.from(email));
    }

    public void matchPassword(final String inputPassword) {
        assert password != null;
        if (!inputPassword.equals(password.getValue())) {
            throw new IllegalArgumentException("암호가 일치하지 않습니다.");
        }
    }

}

