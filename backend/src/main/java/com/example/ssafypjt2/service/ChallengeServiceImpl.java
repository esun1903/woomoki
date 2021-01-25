package com.example.ssafypjt2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ssafypjt2.dao.ChallengeDao;
import com.example.ssafypjt2.dto.ChallengeDto;

@Service
public class ChallengeServiceImpl implements ChallengeService {
	@Autowired
	private ChallengeDao dao; 
	

	@Override
	public ChallengeDto challengeDetail(ChallengeDto challengeDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int challengeUpdate(ChallengeDto challengeDto) {
		int get =dao.challengeUpdate(challengeDto);
		return get;
	}

	
	@Override
	public ChallengeDto challengeJoin(ChallengeDto challengeDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int challengeDelete(ChallengeDto challengeDto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int challengeInsert(ChallengeDto challengeDto) {
		int get = dao.challengeInsert(challengeDto); 
		
		return get;
	}

}
