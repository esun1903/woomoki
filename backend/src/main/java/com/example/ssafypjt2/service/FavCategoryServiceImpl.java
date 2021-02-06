package com.example.ssafypjt2.service;

import com.example.ssafypjt2.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ssafypjt2.dao.FavCategoryDao;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavCategoryServiceImpl implements FavCategoryService {

	@Autowired
	private FavCategoryDao favcategoryDao;

	@Override
	public int favCategory(FavCategoryDto favCategoryDto) {
		System.out.println("======여기는 카테고리를 받아 넣어놓는 곳 도착했습니다 ~ " + favCategoryDto.getUser_id() + " " + favCategoryDto.getCategory_id());
		
		int get = favcategoryDao.favCategory(favCategoryDto);
		System.out.println(get);
		return get;
	}



	@Override
	public int deletefavCategory(int user_id) {
		int get = favcategoryDao.DeletefavCategory(user_id);
		return get;
	}

	@Override
	public int updatefavCategory(FavCategoryDto favCategoryDto) {
		System.out.println("======여기는 카테고리를 수정하는 곳에 도착했습니다 ~ " + favCategoryDto.getUser_id() + " " + favCategoryDto.getCategory_id());
		
		//관심카테고리 넣기 
		int get = favcategoryDao.favCategory(favCategoryDto);
		System.out.println("카테고리 수정 변경 결과" +get);
		return get;
	}

	@Override
	public List<ChallengeDto> main_favCategory(int user_id) {
		List<FavCategoryDto> favCategoryDto = favcategoryDao.main_favCategory1(user_id); // user_id가 가장 좋아하는 카테고리의 id를 갖고오기

		System.out.println(favCategoryDto);

		List<ChallengeDto> list = new ArrayList<ChallengeDto>();

		for (int i = 0; i < favCategoryDto.size(); i++) {
			int id = favCategoryDto.get(i).getCategory_id();

			//followingList의 id를 하나 뽑아서 챌린지를 뽑기
			List<ChallengeDto> test = favcategoryDao.main_favCategory2(id);
			System.out.println("test  " + test);
			list.addAll(test);

		}
		return list;
	}


}
