package com.example.ssafypjt2.dto;

import lombok.Data;

@Data
public class JoinedChallengeDto {
  
	private int user_id;
	private int cng_id; 
	private int result;
	private int sum_stamp;
	private String nickname;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getCng_id() {
		return cng_id;
	}

	public void setCng_id(int cng_id) {
		this.cng_id = cng_id;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getSum_stamp() {
		return sum_stamp;
	}

	public void setSum_stamp(int sum_stamp) {
		this.sum_stamp = sum_stamp;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
