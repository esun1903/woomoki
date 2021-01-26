package com.example.ssafypjt2.service;

import org.springframework.stereotype.Service;

import com.example.ssafypjt2.dto.FavCategoryDto;
import com.example.ssafypjt2.dto.UserDto;

@Service
public interface FavCategoryService {
   
	public int favCategory(FavCategoryDto favCategoryDto);
	public int deletefavCategory(int user_id);
	public int updatefavCategory(FavCategoryDto favCategoryDto);
	
}
