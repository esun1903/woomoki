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
    public int notificationCallCheck(int id) {
        return dao.notificationCallCheck(id);
    }

    @Override
    public int notificationFollow(int notificationId, int followId, String type) {
        return dao.notificationFollow(notificationId, followId, type);
    }

    @Override
    public int notificationChallenge(int notificationId, int cngUserId, int cngId, String type) {
        return dao.notificationChallenge(notificationId, cngUserId, cngId,type);
    }

    @Override
    public int notificationDelete(int notificationId) {
        return dao.notificationDelete(notificationId);
    }


}
