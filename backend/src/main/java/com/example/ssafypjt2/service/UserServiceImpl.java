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

	@Override
	public int changepassword(int user_id, String user_password) {
		System.out.println("여기는 비밀번호를 변경하는 Service 도달했습니다 ~ " + user_id +" password"+ user_password);
        int get = dao.changepassword(user_id ,user_password);
		return get;
	}

	@Override
	public int changenickname(int user_id, String user_newNickname) throws Exception {
		System.out.println("여기는 닉네임을 변경하는 Service 도달했습니다 ~ " + user_id +" password"+ user_newNickname);
		int get = dao.changenickname(user_id, user_newNickname);
		return get;
	}

	@Override
	public int changeintroduce(int user_id, String user_newIntroduce) throws Exception {
		System.out.println("여기는 한 줄 소개 변경하는 Service 도달했습니다 ~ " + user_id +"새로운 한 줄 소개: "+ user_newIntroduce);
		int get = dao.changeintroduce(user_id, user_newIntroduce);
		return get;
	}
}
