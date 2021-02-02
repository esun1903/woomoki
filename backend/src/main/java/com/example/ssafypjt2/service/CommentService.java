package com.example.ssafypjt2.service;

import com.example.ssafypjt2.dto.ChallengeDto;
import com.example.ssafypjt2.dto.CommentDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    public CommentDto commentDetail(int id);
    public int commentInsert(CommentDto commentDto);
}
