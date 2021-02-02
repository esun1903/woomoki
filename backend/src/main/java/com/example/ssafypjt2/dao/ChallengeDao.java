package com.example.ssafypjt2.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.example.ssafypjt2.dto.ChallengeDto;



@Mapper
public interface ChallengeDao {

	@Select(" SELECT category_id, user_id, title, content, "
			+ "sum_img, start_date, end_date, cert_count, "
			+ "max_people, example_img , join_deposit, like_cnt, writer"
			+ " FROM challenge WHERE id = #{cngId} ")
	public ChallengeDto challengeDetail(@Param("cngId")int cngId);


	@Insert("Insert INTO challenge ( category_id, user_id, title, content, sum_img, start_date, end_date, cert_count, max_people, example_img, join_deposit, like_cnt, writer)"
			+ " VALUES ( "
			+ "#{challengeDto.category_id}, #{challengeDto.user_id}, "
			+ "#{challengeDto.title}, #{challengeDto.content}, #{challengeDto.sum_img}, "
			+ "now(), now(), #{challengeDto.cert_count}, "
			+ "#{challengeDto.max_people}, #{challengeDto.example_img}, #{challengeDto.join_deposit}, '0', 'Y')")
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
			+ "WHERE id=#{cngId}")
	public int challengeDelete(@Param("cngId")int cngId);
	
	
	@Select("SELECT * FROM challenge")
	public List<ChallengeDto> ChallengeAllList();
	
	@Select(" SELECT id, category_id, user_id, title, content, "
			+ "sum_img, start_date, end_date, cert_count, "
			+ "max_people, example_img , join_deposit, like_cnt, writer "
			+ " FROM challenge WHERE category_id = #{cgId} ")
	public List<ChallengeDto> challengeCategorySort(@Param("cgId")int cgId);

	@Select(" SELECT id, category_id, user_id, title, content, "
			+ "sum_img, start_date, end_date, cert_count, "
			+ "max_people, example_img , join_deposit, like_cnt, writer "
			+ " FROM challenge WHERE user_id = #{userId} ")
	public List<ChallengeDto> challengeUserSelect(@Param("userId")int userId);

	
}
