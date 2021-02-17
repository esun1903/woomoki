package com.example.ssafypjt2.controller;


import com.example.ssafypjt2.dto.JoinedChallengeDto;
import com.example.ssafypjt2.service.JoinedChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class JoinedChallengeController {

    @Autowired
    JoinedChallengeService service;

    @GetMapping("/joinChallengeUserList/{cngId}")
    public List<JoinedChallengeDto> joinChallengeUserList(@PathVariable(value = "cngId") int cngId){
        return service.joinChallengeUserList(cngId);
    }
}
