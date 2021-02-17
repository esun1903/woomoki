package com.example.ssafypjt2.dao;

import java.util.List;

import com.example.ssafypjt2.dto.ChallengeDto;
import com.example.ssafypjt2.dto.UserDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.ssafypjt2.dto.CertificationDto;


@Mapper
public interface CertificationDao {
	@Select(" SELECT cert.id , cert.cng_id ,  cert.user_id , user.nickname, cng.title , cert.img, cert.content , cert.create_date , cert.result , cert.like_cnt  ,cert.current_week , cert.current_day " +
			" FROM certification cert " +
			" JOIN user ON cert.user_id = user.id " +
			" JOIN challenge cng ON cert.cng_id = cng.id " +
			" WHERE cert.id = #{certId} ")
	public CertificationDto certificationDetail(@Param("certId") int certId);

	@Insert(" Insert INTO certification ( cng_id, img, content, create_date , result, user_id, like_cnt , current_week, current_day )"
			+ " VALUES ( #{certificationDto.cng_id}, #{certificationDto.img}, "
			+ " #{certificationDto.content}, now(), #{certificationDto.result}, #{certificationDto.user_id}, '0' ,#{certificationDto.current_week} , #{certificationDto.current_day}  ) ")
	public int certificationInsert(@Param("certificationDto")CertificationDto certificationDto);

	@Update("Update certification SET "
			+ "cng_id = #{certificationDto.cng_id},"
			+ "img =  #{certificationDto.img},"
			+ "content = #{certificationDto.content},"
			+ "result= #{certificationDto.result},"
			+ "user_id = #{certificationDto.user_id} , "
			+ "current_week = #{certificationDto.current_week}, "
			+ "current_day = #{certificationDto.current_day}  "
			+ "WHERE id = #{certificationDto.id} ")
	public int certificationUpdate(@Param("certificationDto")CertificationDto certificationDto);


	@Delete("DELETE FROM certification "
			+ "WHERE id=#{certId}")
	public int ceriticationDelete(@Param("certId")int certId);


	@Select("SELECT cert.id , cert.cng_id ,  cert.user_id , user.nickname, cng.title , cert.img, cert.content , cert.create_date , cert.result , cert.like_cnt , cert.current_week , cert.current_day " +
			"FROM certification cert " +
			" JOIN user ON cert.user_id = user.id " +
			" JOIN challenge cng ON cert.cng_id = cng.id  ")
	public List<CertificationDto> certificationAllList();

	@Select(" SELECT cert.id , cert.cng_id ,  cert.user_id , user.nickname, cng.title , cert.img, cert.content , cert.create_date , cert.result , cert.like_cnt , cert.current_week , cert.current_day " +
			"FROM certification cert " +
			"JOIN user ON cert.user_id = user.id " +
			"JOIN challenge cng ON cert.cng_id = cng.id " +
			"WHERE cert.cng_id =  #{cngId} ")
	public List<CertificationDto> sameChallengeCrtList(@Param("cngId")int cngId);

	@Select(" SELECT id, cng_id, img, content, create_date , result, user_id, like_cnt, current_week, current_day "
			+ " FROM certification WHERE user_id = #{userId} ")
	public List<CertificationDto> userCrtList(@Param("userId")int userId);

	@Select(" SELECT id, cng_id, img, content, create_date , result, user_id, like_cnt , current_week , current_day "
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

//	@Select(" SELECT cha.id , cha.cng_id , user.nickname, cha.img , cha.content , cha.create_date , cha.result , cha.user_id, cha.like_cnt , cha.current_week, cha.current_day " +
//			" FROM like_certification AS joy  " +
//			" JOIN certification AS cha ON joy.user_id = #{userId} AND joy.cert_id = cha.id " +
//			" JOIN user ON cha.user_id = user.id ")
//	public List<CertificationDto> main_LikeCertificationList(@Param("userId")int userId);

	@Select(" SELECT joy.id , joy.nickname , joy.password , joy.phone , joy.email , joy.introduce , joy.levelnum , joy.leveltitle , joy.img , joy.deposit , joy.join_date\n" +
			"FROM like_certification AS lik " +
			"JOIN user AS joy ON joy.id = lik.user_id " +
			"WHERE lik.cert_id = #{certId}  ")
	public List<UserDto> main_LikeCertificationList(@Param("certId")int certId);

	@Select("SELECT WEEK, DAY FROM challenge WHERE id = #{cngId} ")
    public ChallengeDto challengeWeekDay(@Param("cngId")int cngId);

	@Select("SELECT cert.id , cert.cng_id , user.nickname , cng.title , cert.img , cert.content ,cert.create_date, cert.result ,cert.like_cnt , cert.current_week , cert.current_day " +
			"FROM certification cert JOIN user ON cert.user_id = user.id " +
			"JOIN challenge cng ON cert.cng_id = cng.id " +
			"WHERE cert.user_id = #{userId} AND  cert.cng_id = #{cngId} ")
    public List<CertificationDto> CngCertificationList(@Param("userId")int userId, @Param("cngId")int cngId);

	@Update("UPDATE joined_challenge " +
			"SET sum_stamp = sum_stamp+1  " +
			"WHERE user_id = #{userId} AND cng_id = #{cngId} ")
    public void certificationStamp(@Param("userId")int user_id, @Param("cngId")int cng_id);

	@Select("SELECT sum_stamp FROM joined_challenge WHERE user_id = #{userId} AND cng_id = #{cngId} ")
	public int certificationStampCount(@Param("userId")int user_id, @Param("cngId")int cng_id);

	@Select("SELECT WEEK , DAY  FROM challenge WHERE id = #{cngId}")
	public ChallengeDto challengeDetail(@Param("cngId")int cng_id);

	@Select("UPDATE joined_challenge SET result = 3 WHERE user_id = #{userId} AND cng_id = #{cngId}")
	public void challengeSuccess(@Param("userId") int user_id, @Param("cngId") int cng_id);

	@Select(" UPDATE user SET levelnum = levelnum + 1 WHERE id = #{userId} ")
	public void userLevelUp(@Param("userId") int user_id );

	@Update("UPDATE joined_challenge " +
			"SET sum_stamp = sum_stamp-1  " +
			"WHERE user_id = #{userId} AND cng_id = #{cngId} ")
	public void canclecertificationStamp(@Param("userId") int user_id, @Param("cngId") int cng_id);

	@Select("UPDATE joined_challenge SET result = 2 WHERE user_id = #{userId} AND cng_id = #{cngId}")
	public void challengeFail(@Param("userId") int user_id, @Param("cngId") int cng_id);

	@Select(" UPDATE user SET levelnum = levelnum - 1 WHERE id = #{userId} ")
	public void userLevelDown(@Param("userId") int user_id );
}

