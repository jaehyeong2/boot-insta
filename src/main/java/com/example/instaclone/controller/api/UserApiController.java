package com.example.instaclone.controller.api;

import com.example.instaclone.config.auth.PrincipalDetails;
import com.example.instaclone.domian.user.User;
import com.example.instaclone.dto.CommonResponseDto;
import com.example.instaclone.dto.SubscribeDto;
import com.example.instaclone.dto.UserUpdateDto;
import com.example.instaclone.handler.ex.CustomValidationApiException;
import com.example.instaclone.service.SubscribeService;
import com.example.instaclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;
    private final SubscribeService subscribeService;

    @GetMapping("/api/user/{pageUserId}/subscribe")
    public ResponseEntity<?> subscribeList(@PathVariable int pageUserId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        List<SubscribeDto> subscribeDto = subscribeService.subscribeList(principalDetails.getUser().getId(),pageUserId);

        return new ResponseEntity<>(new CommonResponseDto<>(1,"구독자 정보 불러오기 성공",subscribeDto), HttpStatus.OK);
    }

    @PutMapping("/api/user/{id}")
    public CommonResponseDto<?> update(@PathVariable int id,
                                       @Valid UserUpdateDto userUpdateDto,
                                       BindingResult bindingResult,
                                       @AuthenticationPrincipal PrincipalDetails principalDetails) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationApiException("유효성 검사 실패",errorMap);
        }else{
            User userEntity = userService.modify(id,userUpdateDto.toEntity());
            principalDetails.setUser(userEntity);
            return new CommonResponseDto<>(1,"회원수정완료",userEntity);
        }
    }
}
