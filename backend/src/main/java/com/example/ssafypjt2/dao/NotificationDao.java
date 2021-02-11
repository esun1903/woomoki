package com.example.ssafypjt2.dao;

import com.example.ssafypjt2.dto.CertificationDto;
import com.example.ssafypjt2.dto.ChallengeDto;
import com.example.ssafypjt2.dto.NotificationDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotificationDao {

    @Select("SELECT id, get_user, request_user, type, cng_id, msg, url, create_date, confirm_date FROM notification " +
            "WHERE request_user = #{userId}")
    public List<NotificationDto> notificationList(@Param("userId")int userId);

    @Update("Update notification SET "
            + "confirm_date = now()"
            + "WHERE id = #{notificationId}" )
    public int notificationConfirm(@Param("notificationId")int notificationId);

    @Insert("Insert INTO notification ( get_user, request_user, type, create_date) "
            + " VALUES ( #{userId}, #{followId},#{type}, now())" )
    public int notificationFollow(@Param("userId") int userId, @Param("followId") int followId, @Param("type") String type);

    @Insert("Insert INTO notification ( get_user, request_user, type, cng_Id, create_date) "
            + " VALUES ( #{userId}, #{cngUserId}, #{type}, #{cngId}, now())" )
    public int notificationChallenge(@Param("userId") int userId, @Param("cngUserId") int cngUserId, @Param("cngId") int cngId, @Param("type") String type);

    @Delete("DELETE FROM notification "
            + "WHERE id=#{notificationId}")
    public int notificationDelete(@Param("notificationId")int notificationId);

}
