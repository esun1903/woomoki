package com.example.ssafypjt2.dao;

import com.example.ssafypjt2.dto.CategoryDto;
import com.example.ssafypjt2.dto.ChallengeDto;
import org.apache.ibatis.annotations.*;
import com.example.ssafypjt2.dto.FavCategoryDto;

import java.util.List;

@Mapper
public interface FavCategoryDao {

    @Insert("INSERT INTO fav_category ( user_id, category_id ) VALUES ( #{favCategoryDto.user_id} , #{favCategoryDto.category_id} ) ")
    @Options(useGeneratedKeys = true)
    public int favCategory(@Param("favCategoryDto") FavCategoryDto favCategoryDto);

    @Delete("DELETE FROM fav_category WHERE user_id = #{user_id}")
    public int DeletefavCategory(@Param("user_id") int user_id);

    @Select("SELECT category_id FROM fav_category WHERE user_id = #{user_id}")
    public List<FavCategoryDto> main_favCategory1(@Param("user_id") int user_id);

    @Select("SELECT * FROM  challenge WHERE category_id = #{user_id} ")
    public List<ChallengeDto> main_favCategory2(@Param("user_id") int user_id);

    @Select("SELECT c.name FROM fav_category AS fc INNER JOIN category AS c ON fc.category_id = c.id "
            + "INNER JOIN user u "
            + "ON fc.user_id = u.id "
            + "AND u.nickname = #{nickName}")
    public List<CategoryDto> favCategoryName(@Param("nickName") String nickName);

}
