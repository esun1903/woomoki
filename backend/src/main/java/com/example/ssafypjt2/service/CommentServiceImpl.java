package com.example.ssafypjt2.service;

import com.example.ssafypjt2.dao.ChallengeDao;
import com.example.ssafypjt2.dao.CommentDao;
import com.example.ssafypjt2.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao dao;

    @Override
    public CommentDto commentDetail(int id) {
        return dao.commentDetail(id);
    }

    @Override
    public int commentInsert(CommentDto commentDto) {
        return dao.commentInsert(commentDto);
    }

    @Override
    public int commentUpdate(CommentDto commentDto) {
        return dao.commentUpdate(commentDto);
    }
}
