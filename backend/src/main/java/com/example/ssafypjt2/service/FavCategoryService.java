package com.example.ssafypjt2.service;

import com.example.ssafypjt2.dto.CategoryDto;
import com.example.ssafypjt2.dto.ChallengeDto;
import org.springframework.stereotype.Service;

import com.example.ssafypjt2.dto.FavCategoryDto;
import com.example.ssafypjt2.dto.UserDto;

import java.util.List;

@Service
public interface FavCategoryService {
   
	public int favCategory(FavCategoryDto favCategoryDto);
	public int deletefavCategory(int user_id);
	public int updatefavCategory(FavCategoryDto favCategoryDto);
	public List<ChallengeDto>  main_favCategory(int user_id);
	public List<CategoryDto> favCategoryName(String nickName);
	
}
