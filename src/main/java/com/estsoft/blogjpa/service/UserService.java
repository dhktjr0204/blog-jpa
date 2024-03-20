package com.estsoft.blogjpa.service;


import com.estsoft.blogjpa.domain.dto.AddUserRequest;
import com.estsoft.blogjpa.domain.entity.User;
import com.estsoft.blogjpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public User save(AddUserRequest dto){
        return userRepository.save(
                User.builder()
                        .email(dto.getEmail())
                        .password(encoder.encode(dto.getPassword()))
                        .build()
        );
    }
}
