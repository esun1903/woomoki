package com.ssafy.project.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.project.dao.UserDao;
import com.ssafy.project.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public UserDto login(UserDto userDto) throws Exception {
		return sqlSession.getMapper(UserDao.class).login(userDto);
	}

	@Override
	public int register(UserDto userDto) {
		return sqlSession.getMapper(UserDao.class).register(userDto);
	}

	@Override
	public String getServerInfo() {
		return "사용자에게 전달하고 싶은 중요정보";
	}

}
