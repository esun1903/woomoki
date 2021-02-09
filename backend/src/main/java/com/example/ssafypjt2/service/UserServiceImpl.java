package com.example.ssafypjt2.service;

import com.example.ssafypjt2.dao.UserDao;
import com.example.ssafypjt2.dto.ChallengeDto;
import com.example.ssafypjt2.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
 
	 
	@Autowired
	private UserDao dao;
	
	@Override
	public UserDto login(UserDto userDto) throws Exception {
		System.out.println(userDto.getNickname());
		System.out.println(dao.login(userDto.getEmail()).getJoin_date());
		UserDto result =  dao.login(userDto.getEmail());

		System.out.println("아이디를 받아와 ~ "+ result.getId());
		System.out.println("이메일을 받아와 ~ "+ result.getEmail());
		System.out.println("이메일을 받아와 ~ "+ result.getIntroduce());
		System.out.println("레벨을 받아와 ~ "+ result.getLevelnum());
		return result;
		
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

	@Override
	public int userPageDelete(int user_id) throws Exception {
		System.out.println("여기는 회원탈퇴를 담당하는 Service에 도달했습니다 ~ " + user_id);
		int get = dao.userPageDelete(user_id);
		return get;
	}

	@Override
	public UserDto userPageDetail(int user_id) throws Exception {
		System.out.println("여기는 회원에 대한 상세화면을 담당하는 Service에 도달했습니다. "+ user_id);
		UserDto get = dao.userPageDetail(user_id);
		return get;
	}

	@Override
	public List<ChallengeDto> userPageJoincng(int user_id) throws Exception {
		System.out.println("여기는 회원이 가입된 챌린지들을 보여주는 Service에 도달했습니다 ㅎㅎ "+user_id);
		List<ChallengeDto> list = dao.userPageJoincng(user_id);
		return list;
	}

	//user정보 변경
	@Override
	public int changeUser(UserDto userDto) throws Exception {
		System.out.println("여기는 회원정보를 변경하는 Service 도달하기");
		int  get = dao.changeUser(userDto);
		return get;
	}


}
