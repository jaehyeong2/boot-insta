package com.example.instaclone.dto;

import com.example.instaclone.domian.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateDto {
    @NotBlank
    private String name;

    @NotBlank
    private String password;

    private String website;
    private String bio;
    private String phone;
    private String gender;
    private String email;

}
