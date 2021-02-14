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

	//저의 목표 : 내가 관심있는 챌린지의 id와 스크랩의 수  (스크랩의 수는 : like_cnt 다. like_cnt는 챌린지테이블에 있습니다. )
    // 저는 관심있는 챌린지를 -> challeng 네!

	@Override
	public List<ChallengeDto> user_LikeAndfavChallengeList(int user_id) {

//		//1) 내가 관심있는 챌린지들의 list들을 받아오고
//		System.out.println("유저id"+user_id);
//
//       //네네 근데 관심있는 챌린지에 cng_id, like_cnt 이렇게 받아오려고하는겁니당 !
//		List<FavChallengeDto> favChallengeDto = dao.main_LikefavChallege(user_id); // user_id가 가장 좋아하는 챌린지의 id를 갖고오기
//		System.out.println(favChallengeDto);
//		System.out.println("이게 왜안되는거지?"+ favChallengeDto);
//
//		List<FavChallengeDto> list = new ArrayList<FavChallengeDto>();
//		for (int i = 0; i < favChallengeDto .size(); i++) {
//			int id = favChallengeDto.get(i).getCng_id();
//
//			//2) 그 리스트들 id의 like_cnt를 받아와서
//		 list.add(dao.main_LikefavChallege(id));
//		    //3) 하나의 favChallengeDto에 넣기
//
//		}
		List<ChallengeDto> list = dao.main_LikefavChallege(user_id);


		return list;
	}


}
