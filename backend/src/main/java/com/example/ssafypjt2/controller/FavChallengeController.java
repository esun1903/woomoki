package com.example.ssafypjt2.controller;

import com.example.ssafypjt2.dto.ChallengeDto;
import com.example.ssafypjt2.dto.FavCategoryDto;
import com.example.ssafypjt2.service.FavCategoryService;
import com.example.ssafypjt2.service.FavChallengeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class FavChallengeController {

	@Autowired
	private FavChallengeService favChallengeService;
	public static final Logger logger = LoggerFactory.getLogger(FavChallengeController.class);


	// 장바구니에 챌린지 넣기
	@CrossOrigin(origins = "*")
	@GetMapping("/userPage/favChallenge/{userid}/{cngid}")
	public int user_InsertfavChallenge(@PathVariable(value = "userid") int user_id,
												   @PathVariable(value = "cngid") int cng_id ) throws Exception {

		System.out.println(user_id+"가 "+cng_id+" 를 장바구니에 넣을게~ ");
		int result =  favChallengeService.user_InsertfavChallenge(user_id, cng_id);
		System.out.println(result);
		return result;
	}

	// 장바구니에 챌린지 삭제
	@CrossOrigin(origins = "*")
	@GetMapping("/userPage/DeletefavChallenge/{userid}/{cngid}")
	public int user_DeletefavChallenge(@PathVariable(value = "userid") int user_id,
									@PathVariable(value = "cngid") int cng_id ) throws Exception {

		System.out.println(user_id+"가 "+cng_id+" 장바구니에 있는 것 삭제~  ");
		int result =  favChallengeService.user_DeletefavChallenge(user_id, cng_id);
		System.out.println(result);
		return result;
	}

	//장바구니 챌린지 리스트 보여주기
	@CrossOrigin(origins = "*")
	@GetMapping("/userPage/ListfavChallenge/{userid}")
	public List<ChallengeDto> user_favChallengeList(@PathVariable(value = "userid") int user_id ) throws Exception {

		System.out.println(user_id+"가 관심있는 챌린지들을 보여줄게");
		List<ChallengeDto> result = favChallengeService.user_favChallengeList(user_id);
		System.out.println(result);
		return result;
	}

	//내가 관심있는 챌린지의 id와 스크랩의 수
	@CrossOrigin(origins = "*")
	@GetMapping("/userPage/LikeAndfavChallenge/{userid}")
	public List<ChallengeDto> user_LikeAndfavChallengeList(@PathVariable(value = "userid") int user_id ) throws Exception {

		System.out.println(user_id+"가 관심이있는 챌린지 보여줄게");
		List<ChallengeDto> result = favChallengeService.user_favChallengeList(user_id);
		System.out.println(result);
		return result;
	}




}
