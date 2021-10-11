package com.example.instaclone.handler;


import com.example.instaclone.dto.CommonResponseDto;
import com.example.instaclone.handler.ex.CustomApiException;
import com.example.instaclone.handler.ex.CustomException;
import com.example.instaclone.handler.ex.CustomValidationApiException;
import com.example.instaclone.handler.ex.CustomValidationException;
import com.example.instaclone.util.Script;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e) {

        if(e.getErrorMap()==null) {
            return Script.back(e.getMessage());
        }else{
            return Script.back(e.getErrorMap().toString());
        }
    }

    @ExceptionHandler(CustomException.class)
    public String exception(CustomException e) {
        return Script.back(e.getMessage());
    }

    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<?> validationApiException(CustomValidationApiException e) {
        return new ResponseEntity<>(new CommonResponseDto<>(-1,e.getMessage(),e.getErrorMap()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> apiException(CustomApiException e) {
        return new ResponseEntity<>(new CommonResponseDto<>(-1,e.getMessage(),null),HttpStatus.BAD_REQUEST);
    }
}