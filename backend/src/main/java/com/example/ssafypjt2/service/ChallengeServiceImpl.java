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
	public int challengeDelete(int id) {
		int get = dao.challengeDelete(id);
		return get;
	}

	@Override
	public int challengeInsert(ChallengeDto challengeDto) {
		int get = dao.challengeInsert(challengeDto); 
		
		return get;
	}


	@Override
	public ChallengeDto challengeDetail(int cng_id) {
		return dao.challengeDetail(cng_id);
	}

}
