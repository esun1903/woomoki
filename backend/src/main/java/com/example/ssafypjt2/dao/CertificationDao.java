package com.example.ssafypjt2.dao;

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
	@Select(" SELECT cng_id, img, content, create_date , result"
			+ " FROM certification WHERE id = #{certId} ")
	public CertificationDto certificationDetail(@Param("certId") int certId);

	@Insert("Insert INTO certification ( cng_id, img, content, create_date , result)"
			+ " VALUES ( "
			+ "#{certificationDto.cng_id}, #{certificationDto.img}, "
			+ "#{certificationDto.content}, now(), #{certificationDto.result}) ")			
	public int certificationInsert(@Param("certificationDto")CertificationDto certificationDto);
	
	@Update("Update certification SET "
			+ "cng_id = #{certificationDto.cng_id},"
			+ "img =  #{certificationDto.img}, "
			+ "content = #{certificationDto.content},"
			+ "result= #{certificationDto.result} "
			+ "WHERE id = #{certificationDto.id}")
	public int certificationUpdate(@Param("certificationDto")CertificationDto certificationDto);
	

	@Delete("DELETE FROM certification "
			+ "WHERE id=#{certId}")
	public int challengeDelete(@Param("certId")int certId);
}
