package com.example.ssafypjt2.service;

import java.util.List;

import com.example.ssafypjt2.dto.UserDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import com.example.ssafypjt2.dto.ChallengeDto;

@Service
public interface ChallengeService {


    public int challengeInsert(ChallengeDto challengeDto);

    public int challengeUpdate(ChallengeDto challengeDto);

    public int challengeDelete(int id);

    public ChallengeDto challengeDetail(int id);

    public List<ChallengeDto> challengeAllList();

    public List<ChallengeDto> challengeCategorySort(int cgId);

    public List<ChallengeDto> challengeUserSelect(int userId);

    public ChallengeDto challengeJoin(ChallengeDto challengeDto);

    public  int likeUp(int userId, int cng_id);

    public int likeDown(int userId, int cng_id);

    public List<ChallengeDto> searchWordChallenge(String keyword);

    public List<ChallengeDto> userPageCreatecng(int user_id);

    public  List<String> Challenge();

    public List<ChallengeDto> challengeListResultSort(int user_id , int result_num );

    public  int challengeLikeCount(int cngId);


//    public List<ChallengeDto> user_LikeAndChallengeList(int user_id);

      public List<UserDto> user_LikeAndChallengeList(int user_id);

}

