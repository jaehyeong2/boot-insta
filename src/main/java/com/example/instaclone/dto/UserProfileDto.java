package com.example.instaclone.dto;


import com.example.instaclone.domian.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDto {
    private boolean pageOwnerState;
    private int imageCount;
    private User user;
}
