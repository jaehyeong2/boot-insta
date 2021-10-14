package com.example.instaclone.controller.api;

import com.example.instaclone.config.auth.PrincipalDetails;
import com.example.instaclone.domian.image.Image;
import com.example.instaclone.dto.CommonResponseDto;
import com.example.instaclone.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ImageApiController {

    private final ImageService imageService;

    @GetMapping("/api/image")
    public ResponseEntity<?> imageStory(@AuthenticationPrincipal PrincipalDetails principalDetails,
        @PageableDefault(size=3) Pageable pageable) {
        Page<Image> images = imageService.imageStory(principalDetails.getUser().getId(),pageable);
        return new ResponseEntity<>(new CommonResponseDto<>(1,"성공",images), HttpStatus.OK);
    }

}
