package com.example.instaclone.service;

import com.example.instaclone.domian.likes.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikesService {
    private final LikesRepository likesRepository;

    @Transactional
    public void like(Long imageId, Long principalId){
        likesRepository.mLikes(imageId,principalId);
    }

    @Transactional
    public void unlike(Long imageId, Long principalId){
        likesRepository.mUnlikes(imageId,principalId);
    }
}
