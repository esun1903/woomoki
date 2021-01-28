package com.example.ssafypjt2.service;

import org.springframework.stereotype.Service;
import com.example.ssafypjt2.dto.ChallengeDto;

@Service
public interface ChallengeService {

    public int challengeInsert(ChallengeDto challengeDto); 
    public int challengeUpdate(ChallengeDto challengeDto);
    public int challengeDelete(int id);
    public ChallengeDto challengeDetail(int id);
    
    public ChallengeDto challengeJoin(ChallengeDto challengeDto);
    
    
   
}