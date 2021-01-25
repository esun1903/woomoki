package com.example.ssafypjt2.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.ssafypjt2.dto.ChallengeDto;
import com.example.ssafypjt2.dto.UserDto;

@Mapper
public interface ChallengeDao {

	@Select(" SELECT id AS UserId, nickname AS NickName, password AS PassWord, "
			+ " phone AS Phone, email AS Email, introduce AS Introduce, "
			+ " levelnum AS LevelNum, leveltitle AS LevelTitle, img AS Img, "
			+ " deposit AS Deposit, DATE_FORMAT(join_date,'%Y.%m.%d.') AS userJoin_Date "
			+ " FROM user WHERE email = #{Email} ")
	public UserDto login(String email) throws SQLException;


	@Insert("Insert INTO challenge ( category_id, user_id, title, content, sum_img, start_date, end_date, cert_count, max_people, example_img , join_deposit)"
			+ " VALUES ( "
			+ "#{challengeDto.category_id}, #{challengeDto.user_id}, "
			+ "#{challengeDto.title}, #{challengeDto.content}, #{challengeDto.sum_img}, "
			+ "now(), now(), #{challengeDto.cert_count}, "
			+ "#{challengeDto.max_people}, #{challengeDto.example_img}, #{challengeDto.join_deposit})") 
	@Options(useGeneratedKeys = true)
	public int challengeInsert(@Param("challengeDto")ChallengeDto challengeDto);
	
	@Update("Update challenge SET "
			+ "category_id = #{challengeDto.category_id},"
			+ "user_id =  #{challengeDto.user_id}, "
			+ "title = #{challengeDto.title},"
			+ "content = #{challengeDto.content},"
			+ "sum_img = #{challengeDto.sum_img},"
			+ "max_people = #{challengeDto.max_people},"
			+ "example_img = #{challengeDto.example_img},"
			+ "join_deposit = #{challengeDto.join_deposit} "
			+ "WHERE id = #{challengeDto.id}")
	public int challengeUpdate(@Param("challengeDto")ChallengeDto challengeDto);
	
	@Delete("DELETE FROM challenge "
			+ "WHERE id=#{id}")
	public int challengeDelete(@Param("id")int id);
}

//private int id;
//private int category_id;
//private int user_id;
//private String title;
//private String content;
//private String sum_img;
//private String start_date;
//private String end_date;
//private int cert_count;
//private int max_people;
//private String example;
//private int join_deposit;