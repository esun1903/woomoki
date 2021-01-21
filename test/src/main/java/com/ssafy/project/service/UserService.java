package com.ssafy.project.service;

import com.ssafy.project.dto.UserDto;

public interface UserService {

	public UserDto login(UserDto userDto) throws Exception;
	public int register(UserDto userDto);
	public String getServerInfo();
	
}
