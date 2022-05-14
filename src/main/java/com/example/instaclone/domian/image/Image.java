package com.example.instaclone.domian.image;

import com.example.instaclone.domian.BaseTimeEntity;
import com.example.instaclone.domian.comment.Comment;
import com.example.instaclone.domian.likes.Likes;
import com.example.instaclone.domian.user.User;
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
public class Image extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String caption;
    private String postImageUrl;

    @JsonIgnoreProperties({"images"})
    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;


    @JsonIgnoreProperties({"image"})
    @OneToMany(mappedBy = "image")
    private List<Likes> likes;

    @OrderBy("id DESC ")
    @JsonIgnoreProperties({"image"})
    @OneToMany(mappedBy = "image")
    private List<Comment> comments;

    private LocalDateTime createDate;

    @Transient
    private boolean likeState;

    @Transient
    private int likeCount;

    public Image(Long imageId){
        this.id = imageId;
    }

    public void updateLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public void updateLikeState(boolean check) {
        this.likeState = check;
    }
}
