package com.codingmogul.microservices.userservice.ui.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "from")
public class SignInResponse {
    private final String token;
}
