package com.example.ssafypjt2.dao;

import java.sql.SQLException;
import java.util.List;

import com.example.ssafypjt2.dto.ChallengeDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.ssafypjt2.dto.UserDto;

@Mapper
public interface UserDao {
 
	

	@Select(" SELECT id AS UserId, nickname AS NickName, password AS PassWord, "
			+ " phone AS Phone, email AS Email, introduce AS Introduce, "
			+ " levelnum AS LevelNum, leveltitle AS LevelTitle, img AS Img, "
			+ " deposit AS Deposit, DATE_FORMAT(join_date,'%Y.%m.%d.') AS userJoin_Date "
			+ " FROM user WHERE email = #{Email} ")
	public UserDto login(String email) throws SQLException;

	
	@Insert("Insert INTO user ( nickname, password, phone, email, introduce, levelnum, leveltitle, img, deposit, join_date )"
			+ " VALUES ( #{userDto.nickname} , #{userDto.password}, #{userDto.phone}, #{userDto.email}, #{userDto.introduce}, #{userDto.levelnum}," +
			" #{userDto.leveltitle}, #{userDto.img}, #{userDto.deposit} , now())")
	@Options(useGeneratedKeys = true)
	public int signup(@Param("userDto")UserDto userDto);

  
	@Update("UPDATE user SET PASSWORD = #{user_password} WHERE id = #{user_id}")
	public int changepassword(@Param("user_id")int user_id, @Param("user_password")String user_password);

	@Update("UPDATE user SET nickname = #{user_newNickname} WHERE id = #{user_id}")
	public int changenickname(@Param("user_id")int user_id, @Param("user_newNickname")String user_newNickname);

	@Update("UPDATE user SET introduce = #{user_newIntroduce} WHERE id = #{user_id}")
	public int changeintroduce(@Param("user_id") int user_id,  @Param("user_newIntroduce")String user_newIntroduce);

    @Delete("DELETE FROM user WHERE id = #{user_id} ")
	public int userPageDelete(@Param("user_id") int user_id);


	@Select(" SELECT * FROM user WHERE id = #{user_id} ")
	public UserDto userPageDetail(int user_id) ;

	//유저가 등록되어있는 챌린지의 리스트를 반환하기
	@Select ("SELECT cha.id , cha.category_id, cha.user_id, cha.title, cha.title, cha.content, cha.sum_img, cha.start_date, cha.end_date, cha.cert_count, " +
			"cha.max_people, cha.example_img, cha.join_deposit, cha.like_cnt " +
			"FROM joined_challenge AS joy JOIN challenge AS cha " +
			"ON joy.id = #{user_id} AND joy.cng_id = cha.id")
   public   List<ChallengeDto> userPageJoincng(int user_id);
}

/*
 * 
 * private int id; 
	private String nickname;
	private String password; 
	private String phone;
	private String email;
	private String introduce;
	private int levelnum; 
	private String leveltitle;
	private String img; 
	private int deposit;
 * */
 