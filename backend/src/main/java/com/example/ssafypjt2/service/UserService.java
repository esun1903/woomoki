package com.example.ssafypjt2.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.ssafypjt2.dto.UserDto;

@Service
public interface UserService {
 
	public UserDto login(UserDto userDto) throws Exception;
    public int signup(UserDto userDto);
	public int changepassword( int user_id,  String user_password) throws Exception;
	public int changenickname(int user_id, String user_newNickname) throws Exception;
	public int changeintroduce(int user_id, String user_newIntroduce) throws Exception;
    
}
