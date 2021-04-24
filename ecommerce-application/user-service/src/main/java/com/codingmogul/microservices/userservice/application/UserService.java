package com.codingmogul.microservices.userservice.application;

import com.codingmogul.microservices.userservice.domain.User;
import com.codingmogul.microservices.userservice.domain.UserRepository;
import com.codingmogul.microservices.userservice.ui.dto.SignInRequest;
import com.codingmogul.microservices.userservice.ui.dto.SignUpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void signUp(SignUpRequest request) {
        verifyDuplicatedUser(request.getEmail());
        final User newUser = User.create(
                request.getUsername(), request.getPassword(), request.getEmail());
        userRepository.save(newUser);
    }

    private void verifyDuplicatedUser(String email) {
        if(userRepository.findByEmail(email).isPresent())
                throw new IllegalArgumentException("중복된 유저입니다.");
    }

    public User signIn(SignInRequest request) {
        final User foundUser = userRepository.findByEmail(request.getInputEmail())
                .orElseThrow(() -> new IllegalArgumentException("없는 유저입니다."));

        foundUser.verifyPassword(request.getInputPassword());
        return foundUser;
    }
}
