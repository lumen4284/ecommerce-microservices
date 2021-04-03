package com.codingmogul.microservices.userservice.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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
    @NotNull
    private final String username;
    @NotNull
    private final String password;
    @NotNull
    private final String email;

    public User() {
        this.username = null;
        this.password = null;
        this.email = null;
    }

    public static User create(String username, String password, String email) {
        return new User(username, password, email);
    }

    public void verifyPassword(final String inputPassword){
        if(!inputPassword.equals(password)){
            throw new IllegalArgumentException("암호가 일치하지 않습니다.");
        }
    }

}

