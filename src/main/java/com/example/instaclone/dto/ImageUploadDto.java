package com.example.instaclone.dto;

import com.example.instaclone.domian.image.Image;
import com.example.instaclone.domian.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadDto {

    private MultipartFile file;
    private String caption;

    public Image toEntity(String postImageUrl,User user) {
        return Image.builder()
                .caption(caption)
                .postImageUrl(postImageUrl)
                .user(user)
                .build();
    }
}
