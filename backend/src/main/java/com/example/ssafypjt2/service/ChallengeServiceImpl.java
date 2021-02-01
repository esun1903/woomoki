package com.example.ssafypjt2.service;

import java.util.List;

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
		int get = dao.challengeUpdate(challengeDto);
		return get;
	}

	@Override
	public ChallengeDto challengeJoin(ChallengeDto challengeDto) {
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
	public ChallengeDto challengeDetail(int id) {
		return dao.challengeDetail(id);
	}

	@Override
	public List<ChallengeDto> challengeAllList() {
		return dao.ChallengeAllList();
	}

	@Override
	public List<ChallengeDto> challengeCategorySort(int cgId) {
		return dao.challengeCategorySort(cgId);
	}

	@Override
	public List<ChallengeDto> challengeUserSelect(int userId) {
		return dao.challengeUserSelect(userId);
	}

	@Override
	public List<ChallengeDto> searchWordChallenge(String keyword) {
		System.out.println("키워드로 챌린지 찾기"+keyword);
		return dao.searchWordChallenge(keyword);
	}

}
