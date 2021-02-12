package com.example.ssafypjt2.controller;

import java.util.List;

import com.example.ssafypjt2.dto.CertificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ssafypjt2.dto.RelationDto;
import com.example.ssafypjt2.service.JwtService;
import com.example.ssafypjt2.service.RelationService;

@RestController
public class RelationController {

	@Autowired
	RelationService relationService;
	
	@Autowired
	JwtService jwtService;

	@CrossOrigin(origins = "*")
	@GetMapping("/followerList/{userid}")
	public List<RelationDto> followerList(@PathVariable(value = "userid") int user_id ) {
		//String token = request.getHeader("Authorization");
	//	jwtService.checkValid(token);
		
	//	UserDto user = (UserDto) jwtService.get(token).get("user");
		//System.out.println(user.getNickname());
		System.out.println("이제 "+ user_id +"팔로워리스트를 알려줄게요!");
		
        List<RelationDto> followerList = relationService.followerlist(user_id);
		System.out.println(followerList);
		return followerList;
		
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/followingList/{userid}")
	public List<RelationDto> followingList(@PathVariable(value = "userid") int user_id ) {

		System.out.println("이제 "+ user_id +"팔로잉 리스트를 알려줄게요!");
        List<RelationDto> followingList = relationService.followinglist(user_id);
		return followingList;
	}

	
	@CrossOrigin(origins = "*")
	@DeleteMapping("/followingList/delete/{userid}/{deleteid}")
	public int followingDelete(@PathVariable(value = "userid") int user_id, @PathVariable(value = "deleteid") int delete_id) {

		System.out.println("이제 "+ user_id +"의 팔로잉 리스트를 삭제할게요 !" + "삭제하고 싶은 사람의 id는 " +delete_id);
        int result = relationService.followingDelete(user_id, delete_id);
		return result;
	}
	
	
	@CrossOrigin(origins = "*")
	@DeleteMapping("/followerList/delete/{userid}/{deleteid}")
	public int followerDelete(@PathVariable(value = "userid") int user_id, @PathVariable(value = "deleteid") int delete_id) {

		System.out.println("이제 "+ user_id +"의 팔로워 리스트를 삭제할게요 !" + "삭제하고 싶은 사람의 id는 " +delete_id);
        int result = relationService.followerDelete(user_id, delete_id);
		return result;
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/followingList/Insert/{userid}/{followingid}")
	public int followingInsert(@PathVariable(value = "userid") int user_id, @PathVariable(value = "followingid" ) int following_id) {

		System.out.println("이제 "+ user_id +" 가 " + "팔로잉 하고 싶은  id는 " +following_id);
        int result = relationService.followingInsert(user_id, following_id);
		return result;
	}
	

	//

	@CrossOrigin(origins = "*")
	@GetMapping("/feed/follower/{userid}")
	public List<CertificationDto> feedFollower(@PathVariable(value = "userid") int user_id ) {

		System.out.println("이제 "+ user_id +"가 팔로잉하는 사람들의 인증 feed를 보여줄게요 !");
		List<CertificationDto> List = relationService.feedFollower(user_id);
		return List;
	}

	
	
}
