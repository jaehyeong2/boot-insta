package com.example.instaclone.service;

import com.example.instaclone.domian.subscribe.SubscribeRepository;
import com.example.instaclone.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;

    @Transactional
    public void subscribe(int fromUserId, int toUserId){
        try{
            subscribeRepository.mSubscribe(fromUserId,toUserId);
        }catch(Exception e) {
            throw new CustomApiException("이미 구독중입니다");
        }
    }

    @Transactional
    public void unsubscribe(int fromUserId, int toUserId){
        subscribeRepository.muNSubscribe(fromUserId, toUserId);
    }
}
