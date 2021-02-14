package com.example.ssafypjt2.dto;

import lombok.Data;


@Data
public class FavChallengeDto {

	private int user_id;
	private int cng_id;
	private int like_cnt; // 이렇게

	public FavChallengeDto (int user_id, int cng_id, int like_cnt) {
		super();
		this.user_id = user_id;
		this.cng_id = cng_id;
		this.like_cnt = like_cnt;
	}

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

	public int getLike_cnt() {
		return cng_id;
	}

	public void setLike_cnt(int cng_id) {
		this.like_cnt = like_cnt;
	}

}
