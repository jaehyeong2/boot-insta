package com.example.instaclone.service;

import com.example.instaclone.domian.user.User;
import com.example.instaclone.domian.user.UserRepository;
import com.example.instaclone.dto.UserProfileDto;
import com.example.instaclone.handler.ex.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional(readOnly = true)
    public UserProfileDto userProfile(int pageUserId, int principalId) {
        UserProfileDto dto = new UserProfileDto();

        User userEntity = userRepository.findById(pageUserId).orElseThrow(() ->{
            throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
        });

        dto.setUser(userEntity);
        dto.setPageOwnerState(pageUserId == principalId);
        dto.setImageCount(userEntity.getImages().size());
        return dto;
    }

    @Transactional
    public User modify(int id, User user){
        User userEntity = userRepository.findById(id).orElseThrow(() -> {
                return new IllegalArgumentException("찾을 수 없는 id입니다/");
        });

        userEntity.setName(user.getName());

        String rawPassword = user.getPassword();
        String encodedPassword = encoder.encode(rawPassword);
        userEntity.setPassword(encodedPassword);

        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());

        return userEntity;
    }

}
