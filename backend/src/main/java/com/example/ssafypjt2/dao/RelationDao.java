package com.example.ssafypjt2.dao;

import java.util.List;

import com.example.ssafypjt2.dto.CertificationDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.ssafypjt2.dto.RelationDto;

@Mapper
public interface RelationDao {

	@Select("SELECT nickname FROM user AS gg JOIN relation AS s ON s.get_user_id = #{user_id} AND s.request_user_id = gg.id")
	List<RelationDto> followerlist(@Param("user_id") int user_id);
	
	@Select("SELECT nickname FROM user AS gg JOIN relation AS s ON s.request_user_id = #{user_id} AND s.get_user_id = gg.id")
	List<RelationDto> followinglist(@Param("user_id") int user_id);

	@Delete("DELETE FROM relation WHERE request_user_id = #{user_id} AND get_user_id = #{delete_id} ")
	int followingDelete(@Param("user_id") int user_id,@Param("delete_id") int delete_id);

	@Delete("DELETE FROM relation WHERE request_user_id =#{delete_id} AND get_user_id = #{user_id} ")
	int followerDelete(@Param("user_id") int user_id,@Param("delete_id") int delete_id);

    @Insert("INSERT INTO relation (request_user_id, get_user_id) VALUES (#{user_id} ,#{following_id} ) ")
	int followingInsert(@Param("user_id") int user_id, @Param("following_id")  int following_id);



    @Select("SELECT * FROM certification WHERE user_id = #{user_id} ")
	List<CertificationDto> feedFollower(@Param("user_id")int user_id);

    @Select("SELECT gg.id FROM user AS gg JOIN relation AS s ON s.request_user_id = #{user_id} AND s.get_user_id = gg.id  ")
 	List<CertificationDto>  user_followingList(@Param("user_id")int user_id);
}
