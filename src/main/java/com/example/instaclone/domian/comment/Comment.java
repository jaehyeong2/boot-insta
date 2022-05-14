package com.example.instaclone.domian.comment;


import com.example.instaclone.domian.BaseTimeEntity;
import com.example.instaclone.domian.image.Image;
import com.example.instaclone.domian.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
public class Comment extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = true)
    private String content;

    @JsonIgnoreProperties({"images"})
    @JoinColumn(name="userId")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @JoinColumn(name="imageId")
    @ManyToOne(fetch = FetchType.EAGER)
    private Image image;

    public static Comment create(String content,Image image,User user){
        return Comment.builder()
                .content(content)
                .image(image)
                .user(user)
                .build();
    }

}
