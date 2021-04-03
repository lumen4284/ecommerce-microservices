package com.codingmogul.microservices.userservice.application;

import com.codingmogul.microservices.userservice.domain.User;
import com.codingmogul.microservices.userservice.ui.dto.UserRegisterRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.codingmogul.microservices.userservice.infrastructure.validator.RequestValidator.checkNullFields;

@Service
public class UserService {
    @Transactional
    public void registerUser(UserRegisterRequest request){
        User user = User.create(request.getUsername(), request.getPassword(), request.getEmail());
    }

}
