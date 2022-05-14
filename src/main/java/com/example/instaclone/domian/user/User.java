package com.example.instaclone.domian.user;

import com.example.instaclone.domian.BaseTimeEntity;
import com.example.instaclone.domian.image.Image;
import com.example.instaclone.dto.UserUpdateDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String website;
    private String bio;
    private String phone;
    private String gender;

    private String profileImageUrl;
    private String role;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"user"})
    private List<Image> images;

    public void updatePasswordAndRole(String password,String role){
        this.password = password;
        this.role = role;
    }

    public void updateUserInfo (UserUpdateDto dto,String password) {
        this.password = password;
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.bio = dto.getBio();
        this.phone = dto.getPhone();
        this.gender = dto.getGender();
    }

    public void updateProfileImageUrl(String imageFileName) {
        this.profileImageUrl = imageFileName;
    }
}
