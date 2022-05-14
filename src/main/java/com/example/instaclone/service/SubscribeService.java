package com.example.instaclone.service;

import com.example.instaclone.domian.subscribe.SubscribeRepository;
import com.example.instaclone.dto.SubscribeDto;
import com.example.instaclone.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final EntityManager em;

    @Transactional
    public void subscribe(Long fromUserId, Long toUserId){

        try{
            subscribeRepository.mSubscribe(fromUserId,toUserId);
        }catch(Exception e) {
            throw new CustomApiException("이미 구독중입니다");
        }
    }

    @Transactional
    public void unsubscribe(Long fromUserId, Long toUserId){
        subscribeRepository.muNSubscribe(fromUserId, toUserId);
    }


    @Transactional(readOnly = true)
    public List<SubscribeDto> subscribeList(Long principalId, Long pageUserId) {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT u.id, u.username, u.profileImageUrl,");
        sb.append("if ((SELECT 1 FROM subscribe WHERE fromUserId = ? AND toUserId = u.id),1,0) subscribeState,");
        sb.append("if ((?=u.id),1,0) equalUserState");
        sb.append("FROM user u INNER JOIN subscribe s");
        sb.append("ON u.id=s.toUserId");
        sb.append("WHERE s.fromUserId =?");

        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1,principalId)
                .setParameter(2,principalId)
                .setParameter(3,pageUserId);

        JpaResultMapper result = new JpaResultMapper();
        List<SubscribeDto> subscribeDtos = result.list(query,SubscribeDto.class);
        return null;
    }
}
