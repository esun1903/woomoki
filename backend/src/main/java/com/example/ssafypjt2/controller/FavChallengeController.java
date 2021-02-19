package com.example.ssafypjt2.controller;

import com.example.ssafypjt2.dto.ChallengeDto;

import com.example.ssafypjt2.service.FavChallengeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FavChallengeController {

    @Autowired
    private FavChallengeService favChallengeService;
    public static final Logger logger = LoggerFactory.getLogger(FavChallengeController.class);

    @CrossOrigin(origins = "*")
    @GetMapping("/userPage/favChallenge/{userid}/{cngid}")
    public int user_InsertfavChallenge(@PathVariable(value = "userid") int user_id,
                                       @PathVariable(value = "cngid") int cng_id) throws Exception {
        int result = favChallengeService.user_InsertfavChallenge(user_id, cng_id);
        System.out.println(result);
        return result;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/userPage/DeletefavChallenge/{userid}/{cngid}")
    public int user_DeletefavChallenge(@PathVariable(value = "userid") int user_id,
                                       @PathVariable(value = "cngid") int cng_id) throws Exception {
        int result = favChallengeService.user_DeletefavChallenge(user_id, cng_id);
        return result;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/userPage/ListfavChallenge/{userid}")
    public List<ChallengeDto> user_favChallengeList(@PathVariable(value = "userid") int user_id) throws Exception {
        List<ChallengeDto> result = favChallengeService.user_favChallengeList(user_id);
        return result;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/userPage/LikeAndfavChallenge/{userid}")
    public List<ChallengeDto> user_LikeAndfavChallengeList(@PathVariable(value = "userid") int user_id) throws Exception {
        List<ChallengeDto> result = favChallengeService.user_LikeAndfavChallengeList(user_id);
        return result;
    }

}
