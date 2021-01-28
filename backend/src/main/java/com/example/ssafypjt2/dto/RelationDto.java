package com.example.ssafypjt2.dto;


public class RelationDto {

	private int request_user_id;
	private int get_user_id;
    private String nickname;
	
    public int getRequest_user_id() {
		return request_user_id;
	}
	public void setRequest_user_id(int request_user_id) {
		this.request_user_id = request_user_id;
	}
	public int getGet_user_id() {
		return get_user_id;
	}
	public void setGet_user_id(int get_user_id) {
		this.get_user_id = get_user_id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	} 
	

}
