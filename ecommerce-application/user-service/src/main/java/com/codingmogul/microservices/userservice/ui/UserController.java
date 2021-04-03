package com.codingmogul.microservices.userservice.ui;


import com.codingmogul.microservices.userservice.application.UserService;
import com.codingmogul.microservices.userservice.ui.dto.UserRegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody UserRegisterRequest request)  {

        //        RequestValidator.checkNullFields(request);
        return ResponseEntity.ok().body("postDto 객체 검증 성공");
    }

}
