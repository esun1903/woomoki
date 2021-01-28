package com.example.ssafypjt2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ssafypjt2.dao.RelationDao;
import com.example.ssafypjt2.dto.RelationDto;

@Service
public class RelationServiceImpl implements RelationService {

	@Autowired
	RelationDao dao; 
	
	@Override
	public List<RelationDto> followerlist(int user_id) {
	   
		return  dao.followerlist(user_id);
	}
	
	@Override
	public List<RelationDto> followinglist(int user_id) {
		// TODO Auto-generated method stub
		return dao.followinglist(user_id);
	}

	@Override
	public int followingDelete(int user_id, int delete_id) {
		// TODO Auto-generated method stub
	    int result = dao.followingDelete(user_id, delete_id);
		return result; 
	}

	@Override
	public int followerDelete(int user_id, int delete_id) {
		// TODO Auto-generated method stub
		 int result = dao.followerDelete(user_id, delete_id);
			return result; 
	}

	@Override
	public int followingInsert(int user_id, int following_id) {
		// TODO Auto-generated method stub
		 int result = dao.followingInsert(user_id, following_id);
			return result; 
	}

}
