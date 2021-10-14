package com.example.instaclone.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubscribeResponseDto {
    private int userId;
    private String username;
    private String profileImageUrl;
    private Integer subscribeState;
    private Integer equalUserState;
}
