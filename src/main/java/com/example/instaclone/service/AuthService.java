package com.example.instaclone.service;

import com.example.instaclone.domian.user.User;
import com.example.instaclone.domian.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;

    public User signin(User user){
        User userEntity = userRepository.save(user);
        return userEntity;
    }
}
