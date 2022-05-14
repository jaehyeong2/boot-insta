package com.example.instaclone.controller;

import com.example.instaclone.config.auth.PrincipalDetails;
import com.example.instaclone.domian.image.Image;
import com.example.instaclone.dto.ImageUploadDto;
import com.example.instaclone.handler.ex.CustomValidationException;
import com.example.instaclone.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class imageController {

    private final ImageService imageService;

    @GetMapping({"/","/image/story"})
    public String story() {
        return "image/story";
    }

    @GetMapping("/image/popular")
    public String popular(Model model) {

        List<Image> images = imageService.popular();
        model.addAttribute("images",model);
        return "image/popular";
    }

    @GetMapping("/image/upload")
    public String upload() {
        return "image/upload";
    }

    @PostMapping("/image/upload")
    public String imageUpload(ImageUploadDto imageUploadDto, @AuthenticationPrincipal PrincipalDetails principalDetails){

        if(imageUploadDto.getFile().isEmpty()){
            throw new CustomValidationException("이미지가 첨부되지않았습니다",null);
        }

        imageService.upload(imageUploadDto,principalDetails);
        return "redirect/user/" + principalDetails.getUser().getId();
    }
}
