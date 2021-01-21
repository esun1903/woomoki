package com.ssafy.project.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.project.dto.UserDto;

@Mapper
public interface UserDao {

	public UserDto login(UserDto userDto) throws SQLException;
	public int register(UserDto userDto);
}
