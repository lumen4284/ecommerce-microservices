package com.codingmogul.microservices.userservice.application;

import com.codingmogul.microservices.userservice.domain.AuthService;
import com.codingmogul.microservices.userservice.domain.Email;
import com.codingmogul.microservices.userservice.domain.User;
import com.codingmogul.microservices.userservice.domain.UserRepository;
import com.codingmogul.microservices.userservice.ui.dto.SignInRequest;
import com.codingmogul.microservices.userservice.ui.dto.SignInResponse;
import com.codingmogul.microservices.userservice.ui.dto.SignUpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private AuthService authService;
    private UserRepository userRepository;

    public UserService(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @Transactional
    public String signUp(SignUpRequest request) {
        verifyDuplicatedUser(request.getEmail());
        final User newUser = User.create(
                request.getUsername(), request.getPassword(), request.getEmail());
        userRepository.save(newUser);
        return authService.getToken(newUser.getEmail().getValue());
    }

    private void verifyDuplicatedUser(String email) {
        if(userRepository.findByEmail(Email.from(email)).isPresent())
                throw new IllegalArgumentException("중복된 유저입니다.");
    }

    public String signIn(SignInRequest request) {
        final User foundUser = userRepository.findByEmail(Email.from(request.getInputEmail()))
                .orElseThrow(() -> new IllegalArgumentException("없는 유저입니다."));

        foundUser.matchPassword(request.getInputPassword());
        return authService.getToken(foundUser.getEmail().getValue());
    }
}
