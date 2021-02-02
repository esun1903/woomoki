package com.example.ssafypjt2.dao;

import com.example.ssafypjt2.dto.ChallengeDto;
import com.example.ssafypjt2.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommentDao {

    @Select(" SELECT cert_id, user_id, content, create_date, like_cnt "
            + " FROM comment WHERE id = #{comId} ")
    public CommentDto commentDetail(@Param("comId")int comId);

}
