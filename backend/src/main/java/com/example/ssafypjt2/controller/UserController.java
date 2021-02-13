package com.example.ssafypjt2.controller;

import com.example.ssafypjt2.dto.ChallengeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.ssafypjt2.dto.UserDto;
import com.example.ssafypjt2.model.ResponseData;
import com.example.ssafypjt2.service.JwtService;
import com.example.ssafypjt2.service.UserService;

//@RequestMapping("/post")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class UserController {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserService userService;
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody UserDto userDto, HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			UserDto loginUser = userService.login(userDto);
			System.out.println("loginUser 들어가기전에 ! " + loginUser.getEmail() + " " + loginUser.getPassword() + " "+loginUser.getId()
			+loginUser.getIntroduce()+" "+loginUser.getPhone()+" ");
			if (loginUser != null) {
//				jwt.io에서 확인
//				로그인 성공했다면 토큰을 생성한다.
				String token = jwtService.create(loginUser);
				logger.trace("로그인 토큰정보 : {}", token);

				System.out.println("로그인"+loginUser.getId());
//	                           토큰 정보는 response의 헤더로 보내고 나머지는 Map에 담는다.
//             	response.setHeader("auth-token", token);
				resultMap.put("auth-token", token);
				resultMap.put("user-id", loginUser.getId());
				resultMap.put("user-email", loginUser.getEmail());
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "로그인 실패");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/signup")
	public ResponseEntity<Map<String, Object>> signup(@RequestBody UserDto userDto, HttpServletResponse response) { // 회원가입후 로그인이 되게끔 하기

		HttpStatus status = null;
		Map<String, Object> resultMap = new HashMap<>();
		System.out.println("회원가입: " + userDto.getEmail());
		int result = userService.signup(userDto); // result 값은
		System.out.println(result);

		if (result == 1) {
			try {
				UserDto loginUser = userService.login(userDto);
				System.out.println("loginUser 들어가기전에 ! " + loginUser.getEmail() + " " + loginUser.getPassword());
				System.out.println("아이디" + loginUser.getId());
				if (loginUser != null) {
//					jwt.io에서 확인
//					로그인 성공했다면 토큰을 생성한다.
					String token = jwtService.create(loginUser);
					logger.trace("로그인 토큰정보 : {}", token);

//					토큰 정보는 response의 헤더로 보내고 나머지는 Map에 담는다.
//					response.setHeader("auth-token", token);
					resultMap.put("auth-token", token);
					resultMap.put("user-name", loginUser.getNickname());
					resultMap.put("user-email", loginUser.getEmail());
					resultMap.put("user-id", loginUser.getId());
					status = HttpStatus.CREATED;
				} else {
					resultMap.put("message", "로그인 실패");
					status = HttpStatus.ACCEPTED;
				}
			} catch (Exception e) {
				logger.error("로그인 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}

		} // end of if

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	//로그아웃 기능 
	@CrossOrigin(origins = "*")
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		System.out.println("로그아웃 기능");
		session.invalidate();
		return "redirect:/";
	}
	
    //임시비밀번호 발급받기
	@CrossOrigin(origins = "*")
	@PostMapping("/userPage/changePassword")
	public ResponseEntity changepassword(@RequestBody UserDto userDto) throws Exception {
		System.out.println(userDto.getEmail() + " " + userDto.getPassword());
		int result = userService.changepassword(userDto.getEmail(), userDto.getPassword());
		String returnMessage = "비밀번호 변경 실패";
		if(result == 1) {
			returnMessage = "비밀번호 변경 성공";
		}
		return new ResponseEntity<>(new ResponseData(returnMessage, null), HttpStatus.OK);
	}
	
	//닉네임 변경 
	@CrossOrigin(origins = "*")
	@PostMapping("/userPage/changeNickname")
	public ResponseEntity changenickname(@RequestBody UserDto userDto) throws Exception {
		System.out.println("닉네임변경"+ userDto.getId() + " " + userDto.getNewNickname());
		int result = userService.changenickname(userDto.getId(), userDto.getNewNickname());
		String returnMessage = "닉네임 변경 실패";
		if(result == 1) {
			returnMessage = "닉네임 변경 성공";
		}
		return new ResponseEntity<>(new ResponseData(returnMessage, null), HttpStatus.OK);
	}
	
	//한 줄소개 변경 
	@CrossOrigin(origins = "*")
	@PostMapping("/userPage/changeIntroduce")
	public ResponseEntity changeIntroduce(@RequestBody UserDto userDto) throws Exception {
		System.out.println("한 줄 소개 변경"+ userDto.getId() + " " + userDto.getNewIntroduce());
		
		int result = userService.changeintroduce(userDto.getId(), userDto.getNewIntroduce());
		String returnMessage = "한 줄 소개 변경 실패";
		if(result == 1) {
			returnMessage = "한 줄 소개 변경 성공";
		}
		return new ResponseEntity<>(new ResponseData(returnMessage, null), HttpStatus.OK);
	}
	
	//회원 탈퇴 
	@CrossOrigin(origins = "*")
	@DeleteMapping("/userPage/{userid}")
	public int userPageDelete(@PathVariable(value = "userid") int user_id ) throws Exception {
		System.out.println(user_id + " 유저에 대한 정보 삭제 ");
		
		int result = userService.userPageDelete(user_id);
		String returnMessage = "회원탈퇴 실패 ";
		if(result == 1) {
			returnMessage = "회원탈퇴 성공";
		}
		return result;
	}

	//유저의 아이디를 알려주면 유저에 대한 정보를 주는 Detail
	@CrossOrigin(origins = "*")
	@GetMapping("/userPage/{nickname}")
	public UserDto userPageDetail(@PathVariable(value = "nickname") String nickname ) throws Exception {

		System.out.println(nickname+"에 대한 정보를 알려줄게!");

		UserDto result = userService.userPageDetail(nickname);
		System.out.println(result);
		return result;
	}

	//유저정보의 가입된 챌린지를 보여주는 기능 !
	@CrossOrigin(origins = "*")
	@GetMapping("/userPage/joincng/{userid}")
	public List<ChallengeDto> userPageJoincng(@PathVariable(value = "userid") int user_id ) throws Exception {

		System.out.println(user_id+"가 가입한 챌린지들을 보여줄게");
		List<ChallengeDto> result = userService.userPageJoincng(user_id);
		System.out.println(result);
		return result;
	}

	//User의 정보변경하기
	@CrossOrigin(origins = "*")
	@PostMapping("/userPage/changeUser")
	public ResponseEntity  changeUser(@RequestBody UserDto userDto) throws Exception {
		System.out.println(userDto.getId()+"의 정보에 대해 변경");
		int result = userService.changeUser(userDto);
		String returnMessage = "정보 변경 실패";
		if(result == 1)
			returnMessage =  "정보 변경 성공";
		System.out.println("변경된 user정보" + result);

		return new ResponseEntity<>(new ResponseData(returnMessage, null), HttpStatus.OK);
	}

}

/*
 * --- 기존 postMapping
 * 
 * @CrossOrigin(origins = "*")
 * 
 * @PostMapping("/signup") public int signup ( @RequestBody UserDto userDto) {
 * // 회원가입후 로그인이 되게끔 하기
 * 
 * System.out.println("회원가입: "+ userDto.getEmail()); int result =
 * userService.signup(userDto); // result 값은 System.out.println(result); return
 * result; }
 */
