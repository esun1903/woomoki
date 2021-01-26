package com.example.ssafypjt2.service;

import org.springframework.stereotype.Service;

import com.example.ssafypjt2.dto.UserDto;

@Service
public interface UserService {
 
	public UserDto login(UserDto userDto) throws Exception;
    public int signup(UserDto userDto);
    
}
