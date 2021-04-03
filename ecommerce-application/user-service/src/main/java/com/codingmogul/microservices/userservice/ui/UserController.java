package com.codingmogul.microservices.userservice.ui;


import com.codingmogul.microservices.userservice.application.UserService;
import com.codingmogul.microservices.userservice.domain.User;
import com.codingmogul.microservices.userservice.ui.dto.SignInRequest;
import com.codingmogul.microservices.userservice.ui.dto.SignUpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
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

    @GetMapping
    public ResponseEntity<User> signIn(SignInRequest request){
        final User foundUser = userService.signIn(request);
        return ResponseEntity.ok().body(foundUser);
    }

}
