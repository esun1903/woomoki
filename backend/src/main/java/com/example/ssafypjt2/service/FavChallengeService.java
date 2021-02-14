package com.example.ssafypjt2.service;

import com.example.ssafypjt2.dto.ChallengeDto;
import com.example.ssafypjt2.dto.FavCategoryDto;
import com.example.ssafypjt2.dto.FavChallengeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FavChallengeService {

	// 장바구니에 챌린지 넣기
	public int user_InsertfavChallenge(int user_id, int cng_id);
	// 장바구니에 챌린지 삭제하기
	public int user_DeletefavChallenge(int user_id, int cng_id);
	// 장바구니에 있는 챌린지를 list형태로 보여주기
	public List<ChallengeDto>  user_favChallengeList(int user_id);
	//내가 관심있는 챌린지의 id와 스크랩의 수
	public List<ChallengeDto> user_LikeAndfavChallengeList(int user_id);
}
