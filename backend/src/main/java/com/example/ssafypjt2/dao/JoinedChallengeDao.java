package com.example.ssafypjt2.dao;

import com.example.ssafypjt2.dto.JoinedChallengeDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface JoinedChallengeDao {


    @Select("SELECT jc.user_id, jc.cng_id, jc.result, jc.sum_stamp, u.nickName " +
            "FROM joined_challenge AS jc INNER JOIN user AS u " +
            "WHERE jc.cng_id = #{cngId}  AND jc.user_id = u.id ")
    public List<JoinedChallengeDto> joinChallengeUserList(@Param("cngId")int cngId);

    @Update("Update joined_challenge SET "
            + "result =  #{result} "
            + "WHERE user_id = #{userId} AND cng_id = ${cngId}")
    public int joinResultUpdate(@Param("userId")int userId, @Param("cngId")int cngId,@Param("result")int result);


}
