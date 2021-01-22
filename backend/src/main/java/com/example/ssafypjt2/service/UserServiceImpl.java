package com.example.ssafypjt2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ssafypjt2.dao.UserDao;
import com.example.ssafypjt2.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
 
	 
	@Autowired
	private UserDao dao; 
	
	@Override
	public UserDto login(UserDto userDto) throws Exception {
		System.out.println(userDto.getNickname());
		System.out.println(dao.login(userDto.getEmail()).getJoin_date());
		return dao.login(userDto.getEmail());
		
	}

	@Override
	public int signup(UserDto userDto) {
		System.out.println("여기는 회원가입 Service~ 도달했습니다 ~ "+userDto.getNickname());
		int get = dao.signup(userDto); 
		System.out.println(get);
		return get;
	}
}
