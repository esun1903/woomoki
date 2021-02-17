package com.example.ssafypjt2.dao;

import com.example.ssafypjt2.dto.CommentDto;
import org.apache.ibatis.annotations.*;
import java.util.List;

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

    @Select(" SELECT c.id, c.cert_id, c.user_id, c.content, c.create_date, c.like_cnt, u.nickname, u.img "
            + " FROM comment AS c  INNER JOIN user AS u " +
            " WHERE c.cert_id = #{certId} AND c.user_id = u.id")
    public List<CommentDto> commentShowList(@Param("certId")int certId);

//    @Update("Update comment SET "
//            +"like_cnt = like_cnt+1 "
//            + "WHERE id = #{comId}")
//    public int likeUp(@Param("comId")int comId);
//
//    @Update("Update comment SET "
//            +"like_cnt = like_cnt-1 "
//            + "WHERE id = #{comId}")
//    public int likeDown(@Param("comId")int comId);
}
