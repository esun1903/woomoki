package com.example.ssafypjt2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ssafypjt2.dao.FavCategoryDao;
import com.example.ssafypjt2.dto.FavCategoryDto;

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

}
