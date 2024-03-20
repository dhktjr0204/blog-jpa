package com.estsoft.blogjpa.controller;

import com.estsoft.blogjpa.domain.dto.AddUserRequest;
import com.estsoft.blogjpa.domain.entity.User;
import com.estsoft.blogjpa.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ac;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void mockMvcSetUp(){
        this.mockMvc= MockMvcBuilders.webAppContextSetup(ac).build();
    }

    @Test
    void signup() throws Exception {
        //given : 회원가입에 필요한 정보 초기화
        AddUserRequest request=new AddUserRequest("test@test.com", "password");

        //when : Post /user
        ResultActions response = mockMvc.perform(post("/user")
                .param("email", request.getEmail())
                .param("password", request.getPassword()));

        //then : 호출 결과 HTTP Status code 302, user 저장 여부 검증
        response.andExpect(status().is3xxRedirection());

        User byEmail = userRepository.findByEmail(request.getEmail()).orElseThrow();
        Assertions.assertNotNull(byEmail);
    }

    @Test
    public void existsTest(){
        boolean exists=userRepository.existsById(1L);


    }

}