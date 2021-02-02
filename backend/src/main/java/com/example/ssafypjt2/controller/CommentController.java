package com.example.ssafypjt2.controller;

import com.example.ssafypjt2.dto.CommentDto;
import com.example.ssafypjt2.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
