package com.codingmogul.microservices.userservice.ui;


import com.codingmogul.microservices.userservice.application.UserService;
import com.codingmogul.microservices.userservice.domain.User;
import com.codingmogul.microservices.userservice.ui.dto.SignInRequest;
import com.codingmogul.microservices.userservice.ui.dto.SignInResponse;
import com.codingmogul.microservices.userservice.ui.dto.SignUpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> signUp(@Valid @RequestBody SignUpRequest request)  {
        userService.signUp(request);
        return ResponseEntity.ok().body("postDto 객체 검증 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<SignInResponse> signIn(@Valid @RequestBody SignInRequest request){
        return ResponseEntity.ok().body(SignInResponse.from(userService.signIn(request)));
    }

}
