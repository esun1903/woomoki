package com.example.ssafypjt2.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import com.example.ssafypjt2.dto.FavCategoryDto;

@Mapper
public interface FavCategoryDao {
 

	@Insert("INSERT INTO fav_category ( user_id, category_id ) VALUES ( #{favCategoryDto.user_id} , #{favCategoryDto.category_id} ) ")
	@Options(useGeneratedKeys = true) // myBatis 에서 자동생성키를 true를 했을 때 
	 public int favCategory(@Param("favCategoryDto")FavCategoryDto favCategoryDto);
}
