package com.example.instaclone.service;

import com.example.instaclone.domian.subscribe.SubscribeRepository;
import com.example.instaclone.domian.user.User;
import com.example.instaclone.domian.user.UserRepository;
import com.example.instaclone.dto.UserProfileDto;
import com.example.instaclone.dto.UserUpdateDto;
import com.example.instaclone.handler.ex.CustomApiException;
import com.example.instaclone.handler.ex.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional(readOnly = true)
    public UserProfileDto userProfile(Long pageUserId, Long principalId) {
        UserProfileDto dto = new UserProfileDto();

        User userEntity = userRepository.findById(pageUserId).orElseThrow(() ->{
            throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
        });

        dto.setUser(userEntity);
        dto.setPageOwnerState(pageUserId == principalId);
        dto.setImageCount(userEntity.getImages().size());

        int subscribeState = subscribeRepository.mSubscribeState(principalId,pageUserId);
        int subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);

        dto.setSubscribeCount(subscribeCount);
        dto.setSubscribeState(subscribeState == 1);

        userEntity.getImages().forEach((image)->{
            image.updateLikeCount(image.getLikes().size());
        });

        return dto;
    }

    @Transactional
    public User modify(Long id, UserUpdateDto dto){
        User findUser = userRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("찾을 수 없는 id입니다/");
        });

        String rawPassword = findUser.getPassword();
        String encodedPassword = encoder.encode(rawPassword);

        findUser.updateUserInfo(dto,encodedPassword);
        return findUser;
    }

    @Value("${file.path}")
    private String uploadFolder;

    @Transactional
    public User profilePhotoChange(Long principalId, MultipartFile profileImageFile) {
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid+"_"+profileImageFile.getOriginalFilename();

        Path imageFilePath = Paths.get(uploadFolder+imageFileName);

        try{
            Files.write(imageFilePath,profileImageFile.getBytes());
        } catch (Exception e){
            e.printStackTrace();
        }

        User userEntity = userRepository.findById(principalId).orElseThrow(()-> {
            throw new CustomApiException("유저를 찾을 수 업습니다");
        });

        userEntity.updateProfileImageUrl(imageFileName);
        return userEntity;
    }

}
