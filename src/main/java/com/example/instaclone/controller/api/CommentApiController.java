package com.example.instaclone.controller.api;

import com.example.instaclone.config.auth.PrincipalDetails;
import com.example.instaclone.domian.comment.Comment;
import com.example.instaclone.dto.CommentDto;
import com.example.instaclone.dto.CommonResponseDto;
import com.example.instaclone.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@Controller
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/api/comment")
    public ResponseEntity<?> commentSave(@RequestBody CommentDto commentDto, @AuthenticationPrincipal PrincipalDetails principalDetails){
        Comment comment = commentService.commentWrite(commentDto.getContent(),commentDto.getImageId(),principalDetails.getUser().getId());
        return new ResponseEntity<>(new CommonResponseDto<>(1,"댓글쓰기성공",comment), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/comment/{id}")
    public ResponseEntity<?> commentDelete(@PathVariable int id){
        return null;
    }

}
