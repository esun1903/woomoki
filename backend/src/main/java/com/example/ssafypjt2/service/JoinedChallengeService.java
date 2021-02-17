package com.example.ssafypjt2.service;

import com.example.ssafypjt2.dto.JoinedChallengeDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JoinedChallengeService {


    public List<JoinedChallengeDto> joinChallengeUserList(int cngId);
    public int joinResultUpdate(int userId, int cngId,int result);
}
