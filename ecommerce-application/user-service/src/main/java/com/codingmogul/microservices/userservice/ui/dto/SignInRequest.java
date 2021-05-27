package com.codingmogul.microservices.userservice.ui.dto;

import com.codingmogul.microservices.userservice.infrastructure.validator.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class SignInRequest {
    @NotNull
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String inputEmail;
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String inputPassword;

}
