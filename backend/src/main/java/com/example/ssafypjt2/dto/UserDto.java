package com.example.ssafypjt2.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
public class UserDto {
  
	private int id; 
	private String nickname;
	private String password; 
	private String phone;
	private String email;
	private String introduce;
	private int levelnum; 
	private String leveltitle;
	private String img; 
	private int deposit;
	private String newPassword;
	private String newNickname; 
	private String newIntroduce; 
	
	public String getNewNickname() {
		return newNickname;
	}
	public void setNewNickname(String newNickname) {
		this.newNickname = newNickname;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime join_date;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public int getLevelnum() {
		return levelnum;
	}
	public void setLevelnum(int levelnum) {
		this.levelnum = levelnum;
	}
	public String getLeveltitle() {
		return leveltitle;
	}
	public void setLeveltitle(String leveltitle) {
		this.leveltitle = leveltitle;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getDeposit() {
		return deposit;
	}
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	public LocalDateTime getJoin_date() {
		return join_date;
	}
	public void setJoin_date(LocalDateTime join_date) {
		this.join_date = join_date;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getNewIntroduce() {
		return newIntroduce;
	}
	public void setNewIntroduce(String newIntroduce) {
		this.newIntroduce = newIntroduce;
	}
	
}
