package com.example.ssafypjt2.service;

import org.springframework.stereotype.Service;

import com.example.ssafypjt2.dto.FavCategoryDto;

@Service
public interface FavCategoryService {
   
	public int favCategory(FavCategoryDto favCategoryDto);
	
}
