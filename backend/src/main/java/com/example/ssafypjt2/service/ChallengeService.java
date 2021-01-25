package com.example.ssafypjt2.service;

import org.springframework.stereotype.Service;
import com.example.ssafypjt2.dto.ChallengeDto;

@Service
public interface ChallengeService {

    public int challengeInsert(ChallengeDto challengeDto); 
    public int challengeUpdate(ChallengeDto challengeDto);
    public int challengeDelete(ChallengeDto challengeDto);
    
    public ChallengeDto challengeDetail(ChallengeDto challengeDto);
    
    public ChallengeDto challengeJoin(ChallengeDto challengeDto);
    
    
   
}
