package com.example.instaclone.service;


import com.example.instaclone.domian.comment.Comment;
import com.example.instaclone.domian.comment.CommentRepository;
import com.example.instaclone.domian.image.Image;
import com.example.instaclone.domian.user.User;
import com.example.instaclone.domian.user.UserRepository;
import com.example.instaclone.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public Comment commentWrite(String content,int imageId, int userId) {

        Image image = new Image();
        image.setId(imageId);

        User userEntity = userRepository.findById(userId).orElseThrow(()-> {
            throw new CustomApiException("유저 아이디를 찾을 수 없습니다");
        });

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setImage(image);
        comment.setUser(userEntity);

        return commentRepository.save(comment);
    }

    @Transactional
    public void commentDelete(int id){
        try{
            commentRepository.deleteById(id);
        }catch (Exception e){
            throw new CustomApiException(e.getMessage());
        }
    }
}
