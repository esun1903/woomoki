package com.example.ssafypjt2.service;

import com.example.ssafypjt2.dao.NotificationDao;
import com.example.ssafypjt2.dto.NotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationDao dao;

    @Override
    public List<NotificationDto> notificationList(int userId) {
        return dao.notificationList(userId);
    }

    @Override
    public int notificationConfirm(int id) {
        return dao.notificationConfirm(id);
    }

    @Override
    public int notificationRequestFollow(int notificationId, int followId) {
        return dao.notificationRequestFollow(notificationId, followId);
    }


}
