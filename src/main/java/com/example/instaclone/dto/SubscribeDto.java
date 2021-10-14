package com.example.instaclone.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubscribeDto {
    private int id;
    private String username;
    private String profileImageUrl;
    private String subscribeState;
    private Integer equalUserState;
}
