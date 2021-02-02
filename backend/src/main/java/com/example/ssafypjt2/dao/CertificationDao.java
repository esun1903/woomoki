package com.example.ssafypjt2.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.ssafypjt2.dto.CertificationDto;
import com.example.ssafypjt2.dto.ChallengeDto;

@Mapper
public interface CertificationDao {
	@Select(" SELECT cng_id, img, content, create_date , result, user_id, like_cnt "
			+ " FROM certification WHERE id = #{certId} ")
	public CertificationDto certificationDetail(@Param("certId") int certId);

	@Insert("Insert INTO certification ( cng_id, img, content, create_date , result, user_id, like_cnt)"
			+ " VALUES ( "
			+ "#{certificationDto.cng_id}, #{certificationDto.img}, "
			+ "#{certificationDto.content}, now(), #{certificationDto.result}, #{certificationDto.user_id}, '0') ")
	public int certificationInsert(@Param("certificationDto")CertificationDto certificationDto);

	@Update("Update certification SET "
			+ "cng_id = #{certificationDto.cng_id},"
			+ "img =  #{certificationDto.img}, "
			+ "content = #{certificationDto.content},"
			+ "result= #{certificationDto.result},"
			+ "user_id= #{certificationDto.user_id} "
			+ "WHERE id = #{certificationDto.id}")
	public int certificationUpdate(@Param("certificationDto")CertificationDto certificationDto);


	@Delete("DELETE FROM certification "
			+ "WHERE id=#{certId}")
	public int challengeDelete(@Param("certId")int certId);
	
	
	@Select("SELECT * FROM certification")
	public List<CertificationDto> certificationAllList();
	
	@Select(" SELECT id, cng_id, img, content, create_date, result, user_id, like_cnt"
			+ " FROM certification WHERE cng_id = #{cngId} ")
	public List<CertificationDto> sameChallengeCrtList(@Param("cngId")int cngId);
	
	@Select(" SELECT id, cng_id, img, content, create_date , result, user_id, like_cnt"
			+ " FROM certification WHERE user_id = #{userId} ")
	public List<CertificationDto> userCrtList(@Param("userId")int userId);
	
	@Select(" SELECT id, cng_id, img, content, create_date , result, user_id, like_cnt"
			+ " FROM certification WHERE user_id = #{userId} AND cng_id = #{cngId} ")
	public List<CertificationDto> userCrtListSort(@Param("userId")int userId ,@Param("cngId")int cngId);
}

