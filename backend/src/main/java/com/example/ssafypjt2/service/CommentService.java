package com.example.ssafypjt2.service;

import com.example.ssafypjt2.dto.ChallengeDto;
import com.example.ssafypjt2.dto.CommentDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    public CommentDto commentDetail(int id);

    public int commentInsert(CommentDto commentDto);

    public int commentUpdate(CommentDto commentDto);

    public int commentDelete(int id);

    public List<CommentDto> commentShowList(int certId);

    public int likeUp(int id);

    public int likeDown(int id);
}
