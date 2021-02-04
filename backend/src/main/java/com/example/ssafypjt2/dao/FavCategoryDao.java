package com.example.ssafypjt2.dao;

import com.example.ssafypjt2.dto.ChallengeDto;
import org.apache.ibatis.annotations.*;

import com.example.ssafypjt2.dto.FavCategoryDto;
import com.example.ssafypjt2.dto.UserDto;

import java.util.List;

@Mapper
public interface FavCategoryDao {
 

	@Insert("INSERT INTO fav_category ( user_id, category_id ) VALUES ( #{favCategoryDto.user_id} , #{favCategoryDto.category_id} ) ")
	@Options(useGeneratedKeys = true) // myBatis 에서 자동생성키를 true를 했을 때 
	 public int favCategory(@Param("favCategoryDto")FavCategoryDto favCategoryDto);

	@Delete("DELETE FROM fav_category WHERE user_id = #{user_id}")
	public int DeletefavCategory(@Param("user_id") int user_id);



	@Select("SELECT category_id FROM fav_category WHERE user_id = #{user_id}")
	public List<FavCategoryDto> main_favCategory1(@Param("user_id") int user_id);

	@Select("SELECT * FROM  challenge WHERE category_id = #{user_id} ")
	public List<ChallengeDto> main_favCategory2(@Param("user_id") int user_id);

//	@Insert("INSERT INTO fav_category ( user_id, category_id ) VALUES ( #{favCategoryDto.user_id} , #{favCategoryDto.category_id} ) ")
//	@Options(useGeneratedKeys = true) // myBatis 에서 자동생성키를 true를 했을 때 
//	public int updatefavCategory(@Param("user_id") int user_id, @Param("favCategoryDto")FavCategoryDto favCategoryDto);

	
}
