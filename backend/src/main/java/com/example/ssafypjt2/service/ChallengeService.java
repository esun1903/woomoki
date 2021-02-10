package com.example.ssafypjt2.service;

import java.util.List;

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

    public int likeUp(int id);

    public int likeDown(int id);

    public List<ChallengeDto> searchWordChallenge(String keyword);

    public List<ChallengeDto> userPageCreatecng(int user_id);

    public  List<String> Challenge();

    public List<ChallengeDto> challengeListResultSort(int user_id , int result_num );
}
