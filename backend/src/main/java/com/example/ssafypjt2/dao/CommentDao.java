package com.example.ssafypjt2.dao;

import com.example.ssafypjt2.dto.CertificationDto;
import com.example.ssafypjt2.dto.ChallengeDto;
import com.example.ssafypjt2.dto.CommentDto;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CommentDao {

    @Select(" SELECT cert_id, user_id, content, create_date, like_cnt "
            + " FROM comment WHERE id = #{comId} ")
    public CommentDto commentDetail(@Param("comId")int comId);

    @Insert("Insert INTO comment ( cert_id, user_id, content, create_date, like_cnt)"
            + " VALUES ( "
            + "#{commentDto.cert_id}, #{commentDto.user_id}, "
            + "#{commentDto.content}, now(), '0') ")
    public int commentInsert(@Param("commentDto") CommentDto commentDto);

    @Update("Update comment SET "
            + "content = #{commentDto.content},"
            + "create_date =  now() "
            + "WHERE id = #{commentDto.id}")
    public int commentUpdate(@Param("commentDto") CommentDto commentDto);

    @Delete("DELETE FROM comment "
            + "WHERE id=#{comId}")
    public int commentDelete(@Param("comId")int comId);


}
