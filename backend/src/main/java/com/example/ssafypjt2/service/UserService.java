package com.example.ssafypjt2.service;

import com.example.ssafypjt2.dto.ChallengeDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.ssafypjt2.dto.UserDto;

import java.util.List;

@Service
public interface UserService {
 
	public UserDto login(UserDto userDto) throws Exception;
    public int signup(UserDto userDto);
	public int changepassword( String user_email ,  String user_password) throws Exception;
	public int changenickname(int user_id, String user_newNickname) throws Exception;
	public int changeintroduce(int user_id, String user_newIntroduce) throws Exception;
	public int userPageDelete(int user_id) throws Exception ;
    public UserDto userPageDetail(String user_id) throws Exception;
	public List<ChallengeDto> userPageJoincng(int user_id)  throws Exception ;
	public int changeUser(UserDto userDto) throws Exception;

    public UserDto userPageIdDetail(int user_id);
    public UserDto userInfo(String phone);
}
