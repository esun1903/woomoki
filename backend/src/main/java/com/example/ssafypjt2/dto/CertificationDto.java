package com.example.ssafypjt2.dto;

public class CertificationDto {

	private int id;
	private int cng_id;
	private int user_id;
	private String img;
	private String content;
	private String create_date;
	private int result;

	public CertificationDto() {
		super();
	}

	public CertificationDto(int id, int cng_id, int user_id, String img, String content, String create_date,
			int result) {
		super();
		this.id = id;
		this.cng_id = cng_id;
		this.user_id = user_id;
		this.img = img;
		this.content = content;
		this.create_date = create_date;
		this.result = result;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCng_id() {
		return cng_id;
	}

	public void setCng_id(int cng_id) {
		this.cng_id = cng_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "CertificationDto [id=" + id + ", cng_id=" + cng_id + ", user_id=" + user_id + ", img=" + img
				+ ", content=" + content + ", create_date=" + create_date + ", result=" + result + "]";
	}

}
