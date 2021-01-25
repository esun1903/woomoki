package com.example.ssafypjt2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ssafypjt2.dto.ChallengeDto;
import com.example.ssafypjt2.service.ChallengeService;

@RestController
public class ChallengeController {

	@Autowired
	private ChallengeService challengeService;
	
	
	
	@CrossOrigin(origins = "*")
	@PostMapping("/insertChallenge")
	public int challengeInsert ( @RequestBody ChallengeDto challengeDto) {
		int result = challengeService.challengeInsert(challengeDto);
		
		System.out.println(result);
		return result;
	}
	
	@CrossOrigin(origins = "*")
	@PutMapping("/updateChallenge")
	public int challengeUpdate ( @RequestBody ChallengeDto challengeDto) {
		int result = challengeService.challengeUpdate(challengeDto);
		System.out.println(result);
		return result;
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping("/deleteChallenge")
	public int challengeUpdate ( @RequestBody int id) {
		int result = challengeService.challengeDelete(id);
		System.out.println(result);
		return result;
	}
}
