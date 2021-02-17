package com.example.ssafypjt2.service;

import com.example.ssafypjt2.dao.JoinedChallengeDao;
import com.example.ssafypjt2.dto.JoinedChallengeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoinedChallengeServiceImpl implements JoinedChallengeService {

    @Autowired
    private JoinedChallengeDao dao;

    @Override
    public List<JoinedChallengeDto> joinChallengeUserList(int cngId) {
        return dao.joinChallengeUserList(cngId);
    }

    @Override
    public int joinResultUpdate(int userId, int cngId,int result) {
        return dao.joinResultUpdate(userId, cngId,result);
    }
}
