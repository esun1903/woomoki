package com.example.ssafypjt2.dto;

public class NotificationDto {
	private int id;
	private int get_user;
	private int request_user;
	private String type;
	private int cng_id;
	private String msg;
	private String url;
	private String create_date;
	private String confirm_date;
	
	public NotificationDto() {
		super();
	}
	public NotificationDto(int id, int get_user, int request_user, String type, int cng_id, String msg, String url,
			String create_date, String confirm_date) {
		super();
		this.id = id;
		this.get_user = get_user;
		this.request_user = request_user;
		this.type = type;
		this.cng_id = cng_id;
		this.msg = msg;
		this.url = url;
		this.create_date = create_date;
		this.confirm_date = confirm_date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGet_user() {
		return get_user;
	}
	public void setGet_user(int get_user) {
		this.get_user = get_user;
	}
	public int getRequest_user() {
		return request_user;
	}
	public void setRequest_user(int request_user) {
		this.request_user = request_user;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getCng_id() {
		return cng_id;
	}
	public void setCng_id(int cng_id) {
		this.cng_id = cng_id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getConfirm_date() {
		return confirm_date;
	}
	public void setConfirm_date(String confirm_date) {
		this.confirm_date = confirm_date;
	}
	@Override
	public String toString() {
		return "NotificationDto [id=" + id + ", get_user=" + get_user + ", request_user=" + request_user + ", type="
				+ type + ", cng_id=" + cng_id + ", msg=" + msg + ", url=" + url + ", create_date=" + create_date
				+ ", confirm_date=" + confirm_date + "]";
	}
	
}
