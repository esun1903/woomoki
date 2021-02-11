package com.example.ssafypjt2.service;

import com.example.ssafypjt2.dto.NotificationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {

    public List<NotificationDto> notificationRequestList (int userId);
}
