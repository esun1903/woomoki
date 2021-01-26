package com.example.ssafypjt2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ssafypjt2.dao.FavCategoryDao;
import com.example.ssafypjt2.dto.FavCategoryDto;
import com.example.ssafypjt2.dto.UserDto;

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
		System.out.println(get);
		return get;
	}




}
