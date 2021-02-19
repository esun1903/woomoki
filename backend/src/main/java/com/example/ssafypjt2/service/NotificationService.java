package com.example.ssafypjt2.service;

import com.example.ssafypjt2.dto.NotificationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {

    public List<NotificationDto> notificationList (int userId);
    public int notificationConfirm (int notificationId);
    public int notificationCallCheck (int notificationId);
    public int notificationFollow (int notificationId ,int followId, String type);
    public int notificationChallenge (int notificationId ,int cngUserId, int cngId, String type);
    public int notificationDelete(int notificationId);
}
