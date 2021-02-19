package com.example.ssafypjt2.controller;

import com.example.ssafypjt2.dto.NotificationDto;
import com.example.ssafypjt2.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping("/notificationList/{userId}")
    public List<NotificationDto> notificationList(@PathVariable int userId) {
        return notificationService.notificationList(userId);
    }

    @PutMapping("/notificationConfirm/{notificationId}")
    public int notificationConfirm(@PathVariable int notificationId) {
        return notificationService.notificationConfirm(notificationId);
    }

    @PutMapping("/notificationCallCheck/{notificationId}")
    public int notificationCallCheck(@PathVariable int notificationId) {
        return notificationService.notificationCallCheck(notificationId);
    }

    @PostMapping("/notificationFollow/{userId}/{followId}/{type}")
    public int notificationFollow(@PathVariable int userId, @PathVariable int followId, @PathVariable String type) {
        return notificationService.notificationFollow(userId, followId, type);
    }

    @PostMapping("/notificationRequestChallenge/{userId}/{cngUserId}/{cngId}/{type}")
    public int notificationChallenge(@PathVariable int userId, @PathVariable int cngUserId, @PathVariable int cngId, @PathVariable String type) {
        return notificationService.notificationChallenge(userId, cngUserId, cngId, type);
    }

    @DeleteMapping("/notificationDelete/{notificationId}")
    public int notificationDelete(@PathVariable int notificationId) {
        return notificationService.notificationDelete(notificationId);
    }
}
