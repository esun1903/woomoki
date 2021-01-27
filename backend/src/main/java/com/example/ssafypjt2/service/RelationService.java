package com.example.ssafypjt2.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.example.ssafypjt2.dto.RelationDto;

@Service
public interface RelationService {

	 //팔로워리스트을 보여줄 followerlist
	List<RelationDto> followerlist(@Param("user_id") int user_id);
	List<RelationDto> followinglist(@Param("user_id") int user_id);
	int followingDelete(@Param("user_id") int user_id, @Param("delete_id") int delete_id);
	int followerDelete(@Param("user_id") int user_id, @Param("delete_id") int delete_id);
	int followingInsert(@Param("user_id") int user_id, @Param("following_id") int following_id);
	
	
}
