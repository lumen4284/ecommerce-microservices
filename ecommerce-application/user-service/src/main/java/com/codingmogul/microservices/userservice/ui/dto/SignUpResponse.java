package com.codingmogul.microservices.userservice.ui.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "from")
public class SignUpResponse {
    private final String token;
}
