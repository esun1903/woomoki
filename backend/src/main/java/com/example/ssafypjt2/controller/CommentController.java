package com.example.ssafypjt2.controller;

import com.example.ssafypjt2.dto.CertificationDto;
import com.example.ssafypjt2.dto.ChallengeDto;
import com.example.ssafypjt2.dto.CommentDto;
import com.example.ssafypjt2.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/detailCommnet/{comId}")
    public CommentDto commentDetail (@PathVariable(value = "comId") int comid) {
        CommentDto result = commentService.commentDetail(comid);
        return result;
    }

    @PostMapping("/insertComment")
    public int commentInsert ( @RequestBody CommentDto commentDto) {
        int result = commentService.commentInsert(commentDto);
        return result;
    }

    @PutMapping("/updateComment")
    public int commentUpdate ( @RequestBody CommentDto commentDto) {
        int result = commentService.commentUpdate(commentDto);
        return result;
    }
}
