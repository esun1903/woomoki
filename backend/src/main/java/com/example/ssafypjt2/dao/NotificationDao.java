package com.example.ssafypjt2.dao;


import com.example.ssafypjt2.dto.NotificationDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotificationDao {

    @Select("SELECT n.id, n.get_user, n.request_user, n.type, n.cng_id, n.msg, n.url, n.create_date, n.confirm_date, " +
            " gu.nickname AS getUserName, ru.nickName AS reqUserName, c.title AS cngTitle " +
            "FROM notification AS n INNER JOIN user AS gu INNER JOIN user AS ru INNER JOIN challenge c " +
            "WHERE n.get_user = #{userId}  AND n.get_user = gu.id AND n.request_user = ru.id AND n.cng_id = c.id")
    public List<NotificationDto> notificationList(@Param("userId") int userId);

    @Update("Update notification SET "
            + "confirm_date = now()"
            + "WHERE id = #{notificationId}")
    public int notificationConfirm(@Param("notificationId") int notificationId);

    @Update("Update notification SET "
            + "msg = '2' "
            + "WHERE id = #{notificationId}")
    public int notificationCallCheck(@Param("notificationId") int notificationId);

    @Insert("Insert INTO notification ( get_user, request_user, type, create_date) "
            + " VALUES ( #{followId}, #{userId}, #{type}, now())")
    public int notificationFollow(@Param("userId") int userId, @Param("followId") int followId, @Param("type") String type);

    @Insert("Insert INTO notification ( get_user, request_user, type, cng_Id, create_date) "
            + " VALUES ( #{cngUserId}, #{userId}, #{type}, #{cngId}, now())")
    public int notificationChallenge(@Param("userId") int userId, @Param("cngUserId") int cngUserId, @Param("cngId") int cngId, @Param("type") String type);

    @Delete("DELETE FROM notification "
            + "WHERE id=#{notificationId}")
    public int notificationDelete(@Param("notificationId") int notificationId);

}
