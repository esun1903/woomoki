package com.example.ssafypjt2.service;

import com.example.ssafypjt2.dao.FavCategoryDao;
import com.example.ssafypjt2.dao.FavChallengeDao;
import com.example.ssafypjt2.dto.ChallengeDto;
import com.example.ssafypjt2.dto.FavCategoryDto;
import com.example.ssafypjt2.dto.FavChallengeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class FavChallengeServiceImpl implements FavChallengeService {

	@Autowired
	private FavChallengeDao dao;

	// 장바구니에 챌린지 넣기
	@Override
	public int user_InsertfavChallenge(int user_id, int cng_id) {
		System.out.println("======여기는 장바구니에 챌린지 넣기!~ " + user_id + " " + cng_id);
		int result = dao.user_InsertfavChallenge(user_id,cng_id);

		return result;
	}

	// 장바구니에 챌린지 삭제하기
	@Override
	public int user_DeletefavChallenge(int user_id, int cng_id) {
		System.out.println("======여기는 장바구니에 챌린지 삭제!~ " + user_id + " " + cng_id);
		int result = dao.user_DeletefavChallenge(user_id,cng_id);

		return result;
	}
	// 장바구니에 있는 챌린지를 list형태로 보여주기
	@Override
	public List<ChallengeDto> user_favChallengeList(int user_id) {

		List<FavChallengeDto> favChallengeDto = dao.main_favChallege1(user_id); // user_id가 가장 좋아하는 챌린지의 id를 갖고오기

		System.out.println(favChallengeDto);

		List<ChallengeDto> list = new ArrayList<ChallengeDto>();

		for (int i = 0; i < favChallengeDto .size(); i++) {
			int id = favChallengeDto.get(i).getCng_id();

			//followingList의 id를 하나 뽑아서 챌린지를 뽑기
			List<ChallengeDto> test = dao.main_favChallege2(id);
			System.out.println("test  " + test);
			list.addAll(test);

		}
		return list;
	}


}
