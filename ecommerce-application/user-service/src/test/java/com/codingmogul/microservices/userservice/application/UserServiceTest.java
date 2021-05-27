package com.codingmogul.microservices.userservice.application;

import com.codingmogul.microservices.userservice.ui.dto.SignInRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
        Assert.notNull(userService);
    }

    @Test
    public void test(){
        String value = userService.signIn(new SignInRequest("game4284@gmail.com", "test123"));
        System.out.println(value);
    }

}