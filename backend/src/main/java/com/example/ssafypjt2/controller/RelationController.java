package com.example.ssafypjt2.controller;

import java.util.List;

import com.example.ssafypjt2.dto.CertificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.ssafypjt2.dto.RelationDto;
import com.example.ssafypjt2.service.JwtService;
import com.example.ssafypjt2.service.RelationService;

@RestController
public class RelationController {

    @Autowired
    RelationService relationService;

    @Autowired
    JwtService jwtService;

    @CrossOrigin(origins = "*")
    @GetMapping("/followerList/{userid}")
    public List<RelationDto> followerList(@PathVariable(value = "userid") int user_id) {
        List<RelationDto> followerList = relationService.followerlist(user_id);
        System.out.println(followerList);
        return followerList;

    }

    @CrossOrigin(origins = "*")
    @GetMapping("/followingList/{userid}")
    public List<RelationDto> followingList(@PathVariable(value = "userid") int user_id) {
        List<RelationDto> followingList = relationService.followinglist(user_id);
        return followingList;
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/followingList/delete/{userid}/{deleteid}")
    public int followingDelete(@PathVariable(value = "userid") int user_id, @PathVariable(value = "deleteid") int delete_id) {
        int result = relationService.followingDelete(user_id, delete_id);
        return result;
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/followerList/delete/{userid}/{deleteid}")
    public int followerDelete(@PathVariable(value = "userid") int user_id, @PathVariable(value = "deleteid") int delete_id) {
        int result = relationService.followerDelete(user_id, delete_id);
        return result;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/followingList/Insert/{userid}/{followingid}")
    public int followingInsert(@PathVariable(value = "userid") int user_id, @PathVariable(value = "followingid") int following_id) {
        int result = relationService.followingInsert(user_id, following_id);
        return result;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/feed/follower/{userid}")
    public List<CertificationDto> feedFollower(@PathVariable(value = "userid") int user_id) {
        List<CertificationDto> List = relationService.feedFollower(user_id);
        return List;
    }
}
