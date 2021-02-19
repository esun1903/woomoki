package com.example.ssafypjt2.controller;

import java.util.List;

import com.example.ssafypjt2.dto.UserDto;
import com.example.ssafypjt2.service.FavCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.ssafypjt2.dto.ChallengeDto;
import com.example.ssafypjt2.service.ChallengeService;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;

    @Autowired
    private FavCategoryService favCategoryService;

    @PostMapping("/insertChallenge")
    public int challengeInsert(@RequestBody ChallengeDto challengeDto) {
        int result = challengeService.challengeInsert(challengeDto);
        return result;
    }

    @PutMapping("/updateChallenge")
    public int challengeUpdate(@RequestBody ChallengeDto challengeDto) {
        int result = challengeService.challengeUpdate(challengeDto);
        return result;
    }

    @DeleteMapping("/deleteChallenge/{cngId}")
    public int challengeUpdate(@PathVariable(value = "cngId") int id) {
        int result = challengeService.challengeDelete(id);
        return result;
    }

    @GetMapping("/detailChallenge/{cngId}")
    public ChallengeDto challengeDetail(@PathVariable(value = "cngId") int id) {
        ChallengeDto result = challengeService.challengeDetail(id);
        return result;
    }

    @GetMapping("/allChallenge")
    public List<ChallengeDto> challengeAllList() {
        return challengeService.challengeAllList();
    }

    @GetMapping("/categorySort/{cgId}")
    public List<ChallengeDto> challengeCategorySort(@PathVariable(value = "cgId") int cgId) {
        return challengeService.challengeCategorySort(cgId);
    }

    @GetMapping("/challengeUserSelect/{userId}")
    public List<ChallengeDto> challengeUserSelect(@PathVariable(value = "userId") int userId) {
        return challengeService.challengeUserSelect(userId);
    }

    @PutMapping("/likeUpChallenge/{userId}/{cngId}")
    public int likeUp(@PathVariable(value = "userId") int userId, @PathVariable(value = "cngId") int cng_id) throws Exception {
        int result = challengeService.likeUp(userId, cng_id);
        return result;
    }

    @PutMapping("/likeDownChallenge/{userId}/{cngId}")
    public int likeDown(@PathVariable(value = "userId") int userId, @PathVariable(value = "cngId") int cng_id) {
        int result = challengeService.likeDown(userId, cng_id);
        return result;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("LikeAndChallenge/{cngId}")
    public List<UserDto> user_LikeAndChallengeList(@PathVariable(value = "cngId") int cng_id) throws Exception {
        List<UserDto> result = challengeService.user_LikeAndChallengeList(cng_id);
        return result;
    }

    @GetMapping("/userPage/createcng/{userid}")
    public List<ChallengeDto> userPageCreatecng(@PathVariable(value = "userid") int user_id) throws Exception {
        List<ChallengeDto> result = challengeService.userPageCreatecng(user_id);
        return result;
    }

    @PostMapping("/")
    public List<ChallengeDto> favCategory(@RequestBody UserDto userDto) {
        List<ChallengeDto> result = favCategoryService.main_favCategory(userDto.getId());
        return result;
    }

    @GetMapping("/searchWordChallenge/{keyword}")
    public List<ChallengeDto> searchWordChallenge(@PathVariable(value = "keyword") String keyword) {
        return challengeService.searchWordChallenge(keyword);
    }

    @GetMapping("/challenge")
    public List<String> challenge() {
        List<String> list = challengeService.Challenge();
        return list;
    }

    @GetMapping("/challengeResultSort/{userId}/{resultNum}")
    public List<ChallengeDto> challengeListResultSort(@PathVariable(value = "userId") int userId, @PathVariable(value = "resultNum") int resultNum) {
        return challengeService.challengeListResultSort(userId, resultNum);
    }

    @GetMapping("/likecount/{cngId}")
    public int challengeLikeCount(@PathVariable(value = "cngId") int cngId) {
        return challengeService.challengeLikeCount(cngId);
    }
}
