package com.example.ssafypjt2.service;

import java.util.ArrayList;
import java.util.List;

import com.example.ssafypjt2.dto.CertificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ssafypjt2.dao.RelationDao;
import com.example.ssafypjt2.dto.RelationDto;

@Service
public class RelationServiceImpl implements RelationService {

    @Autowired
    RelationDao dao;

    @Override
    public List<RelationDto> followerlist(int user_id) {

        return dao.followerlist(user_id);
    }

    @Override
    public List<RelationDto> followinglist(int user_id) {
        // TODO Auto-generated method stub
        return dao.followinglist(user_id);
    }

    @Override
    public int followingDelete(int user_id, int delete_id) {
        // TODO Auto-generated method stub
        int result = dao.followingDelete(user_id, delete_id);
        return result;
    }

    @Override
    public int followerDelete(int user_id, int delete_id) {
        // TODO Auto-generated method stub
        int result = dao.followerDelete(user_id, delete_id);
        return result;
    }

    @Override
    public int followingInsert(int user_id, int following_id) {
        // TODO Auto-generated method stub
        int result = dao.followingInsert(user_id, following_id);
        return result;
    }

    /*
    // 내가 팔로잉하는 사람들의 인증리스트를 가져오기
    1. 내가 팔로잉하는 사람들의 list를 갖고오기
    2. 그 사람들을 int [] 배열로 값을 받는다.
    3. foreach문으로 하나하나 변수를 받아서
        이거를 -> dao.feedFollower형태로 해당 유저에 대한 인증을 리스트형태로 나타내서
    4. 이거를 다시 list형태로 return 한다.
    * */
    @Override
    public List<CertificationDto> feedFollower(int user_id) {
        List<CertificationDto> followingList = dao.user_followingList(user_id);
        System.out.println("user가 팔로잉하고있는사람들" + followingList);

        List<CertificationDto> list = new ArrayList<CertificationDto>();

        for (int i = 0; i < followingList.size(); i++) {
            int id = followingList.get(i).getId(); // 2 -> 3 -> 4

            //followingList의 id를 하나 뽑아서 챌린지를 뽑기
            List<CertificationDto> test = dao.feedFollower(id);
            System.out.println("test  " + test);
            list.addAll(test);

        }
        return list;
    }

}
