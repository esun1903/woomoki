package com.example.ssafypjt2.service;


import com.example.ssafypjt2.dao.CommentDao;
import com.example.ssafypjt2.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public int commentDelete(int id) {
        return dao.commentDelete(id);
    }

    @Override
    public List<CommentDto> commentShowList(int certId) {
        return dao.commentShowList(certId);
    }

    @Override
    public int likeUp(int id) {
        return dao.likeUp(id);
    }

    @Override
    public int likeDown(int id) {
        return dao.likeDown(id);
    }


}
