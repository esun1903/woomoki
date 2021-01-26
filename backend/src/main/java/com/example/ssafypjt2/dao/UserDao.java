package com.example.ssafypjt2.dao;

import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.ssafypjt2.dto.UserDto;

@Mapper
public interface UserDao {

	@Select(" SELECT id AS UserId, nickname AS NickName, password AS PassWord, "
			+ " phone AS Phone, email AS Email, introduce AS Introduce, "
			+ " levelnum AS LevelNum, leveltitle AS LevelTitle, img AS Img, "
			+ " deposit AS Deposit, DATE_FORMAT(join_date,'%Y.%m.%d.') AS userJoin_Date "
			+ " FROM user WHERE email = #{Email} ")
	public UserDto login(String email) throws SQLException;

	// @Insert("INSERT INTO user ( USER_NAME, USER_PASSWORD, USER_EMAIL, USER_REGISTER_DATE ) VALUES ( #{memberDto.userName} , #{memberDto.userPassword} , #{memberDto.userEmail} , now())")
	
	@Insert("Insert INTO user ( nickname, password, phone, email, introduce, levelnum, leveltitle, img, deposit, join_date )"
			+ " VALUES ( #{userDto.nickname} , #{userDto.password}, #{userDto.phone}, #{userDto.email}, #{userDto.introduce}, #{userDto.levelnum}, #{userDto.leveltitle}, #{userDto.img}, #{userDto.deposit} , now())")
	@Options(useGeneratedKeys = true)
	public int signup(@Param("userDto")UserDto userDto);

}

/*
 * 
 * private int id; 
	private String nickname;
	private String password; 
	private String phone;
	private String email;
	private String introduce;
	private int levelnum; 
	private String leveltitle;
	private String img; 
	private int deposit;
 * */
 