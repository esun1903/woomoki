package com.example.ssafypjt2.dao;

import java.util.List;

import com.example.ssafypjt2.dto.ChallengeDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.ssafypjt2.dto.CertificationDto;


@Mapper
public interface CertificationDao {
	@Select(" SELECT cert.id , cert.cng_id ,  cert.user_id , user.nickname, cng.title , cert.img, cert.content , cert.create_date , cert.result , cert.like_cnt " +
			" FROM certification cert " +
			" JOIN user ON cert.user_id = user.id " +
			" JOIN challenge cng ON cert.cng_id = cng.id " +
			" WHERE cert.id = #{certId} ")
	public CertificationDto certificationDetail(@Param("certId") int certId);

	@Insert(" Insert INTO certification ( cng_id, img, content, create_date , result, user_id, like_cnt)"
			+ " VALUES ( #{certificationDto.cng_id}, #{certificationDto.img}, "
			+ " #{certificationDto.content}, now(), #{certificationDto.result}, #{certificationDto.user_id}, '0' , #{certificationDto.week} , #{certificationDto.day} ) ")
	public int certificationInsert(@Param("certificationDto")CertificationDto certificationDto);

	@Update("Update certification SET "
			+ "cng_id = #{certificationDto.cng_id},"
			+ "img =  #{certificationDto.img}, "
			+ "content = #{certificationDto.content},"
			+ "result= #{certificationDto.result},"
			+ "user_id = #{certificationDto.user_id} , "
			+ "week = #{certificationDto.week } , "
			+ "day = #{certificationDto.day} , "
			+ "WHERE id = #{certificationDto.id}")
	public int certificationUpdate(@Param("certificationDto")CertificationDto certificationDto);


	@Delete("DELETE FROM certification "
			+ "WHERE id=#{certId}")
	public int ceriticationDelete(@Param("certId")int certId);


	@Select("SELECT cert.id , cert.cng_id ,  cert.user_id , user.nickname, cng.title , cert.img, cert.content , cert.create_date , cert.result , cert.like_cnt , cert.week , cert.day " +
			"FROM certification cert " +
			" JOIN user ON cert.user_id = user.id " +
			" JOIN challenge cng ON cert.cng_id = cng.id  ")
	public List<CertificationDto> certificationAllList();

	@Select(" SELECT cert.id , cert.cng_id ,  cert.user_id , user.nickname, cng.title , cert.img, cert.content , cert.create_date , cert.result , cert.like_cnt , cert.week , cert.day " +
			"FROM certification cert " +
			"JOIN user ON cert.user_id = user.id " +
			"JOIN challenge cng ON cert.cng_id = cng.id " +
			"WHERE cert.cng_id =  #{cngId} ")
	public List<CertificationDto> sameChallengeCrtList(@Param("cngId")int cngId);

	@Select(" SELECT id, cng_id, img, content, create_date , result, user_id, like_cnt, week, day "
			+ " FROM certification WHERE user_id = #{userId} ")
	public List<CertificationDto> userCrtList(@Param("userId")int userId);

	@Select(" SELECT id, cng_id, img, content, create_date , result, user_id, like_cnt , week , day "
			+ " FROM certification WHERE user_id = #{userId} AND cng_id = #{cngId} ")
	public List<CertificationDto> userCrtListSort(@Param("userId")int userId ,@Param("cngId")int cngId);


	@Update("Update certification SET "
			+"like_cnt = like_cnt+1 "
			+ "WHERE id = #{certId}")
	public int one_likeUp(@Param("certId")int certId);

	@Update("Update certification SET "
			+"like_cnt = like_cnt-1 "
			+ "WHERE id = #{certId}")
	public int one_likeDown(@Param("certId")int certId);


	@Insert("INSERT INTO like_certification ( user_id, cert_id  ) VALUES (#{userId}  , #{certId} )")
	public int likeUp(@Param("userId")int userId, @Param("certId")int certId);


	@Delete("DELETE FROM like_certification WHERE user_id = #{userId} AND cert_id = #{certId}")
	public int likeDown(@Param("userId")int userId, @Param("certId")int certId);


	@Select("SELECT * FROM  certification WHERE content like CONCAT('%', #{keyword}, '%')")
    public List<CertificationDto> searchWordCert(@Param("keyword") String keyword);

	@Select(" SELECT cha.id , cha.cng_id , user.nickname, cha.img , cha.content , cha.create_date , cha.result , cha.user_id, cha.like_cnt , cha.week, cha.day " +
			" FROM like_certification AS joy  " +
			" JOIN certification AS cha ON joy.user_id = #{userId} AND joy.cert_id = cha.id " +
			" JOIN user ON cha.user_id = user.id ")
	public List<CertificationDto> main_LikeCertificationList(@Param("userId")int userId);

	@Select("SELECT WEEK, DAY FROM challenge WHERE id = #{cngId} ")
    public ChallengeDto challengeWeekDay(@Param("cngId")int cngId);

	@Select("SELECT cert.id , cert.cng_id , user.nickname , cng.title , cert.img , cert.content ,cert.create_date, cert.result ,cert.like_cnt " +
			"FROM certification cert JOIN user ON cert.user_id = user.id " +
			"JOIN challenge cng ON cert.cng_id = cng.id " +
			"WHERE cert.user_id = #{userId} AND  cert.cng_id = #{cngId} ")
    public List<CertificationDto> CngCertificationList(@Param("userId")int userId, @Param("cngId")int cngId);
}

