package com.example.ssafypjt2.controller;

import com.example.ssafypjt2.dto.NotificationDto;
import com.example.ssafypjt2.service.NotificationService;
import com.example.ssafypjt2.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping("/notificationList/{userId}")
    public List<NotificationDto> notificationList(@PathVariable int userId ) {
        return notificationService.notificationList(userId);
    }

    @PostMapping("/notificationConfirm/{notificationId}")
    public int notificationConfirm(@PathVariable int notificationId){
        return notificationService.notificationConfirm(notificationId);
    }

	// 개설자가 챌린지 참가를 수락/거절 하기
//	@PostMapping("/challengeJoinResult/{cngid}")
//	public List<NotificationDto> challengeJoinResult(@RequestBody NotificationDto notificationDto, @RequestBody JoinedchallengeDto joinedChallengeDto) {
//		List<NotificationDto> list;
//		return list;
//	}
//
//	// 친구신청 알림
//	@PostMapping("/followResult/{userid}")
//	public List<NotificationDto> followResult(@RequestBody RelationDto relationDto) {
//		List<NotificationDto> list;
//		return list;
//	}
}
