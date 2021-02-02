package com.example.ssafypjt2.dto;

public class ChallengeDto {
	private int id;
	private int category_id;
	private int user_id;
	private String title;
	private String content;
	private String sum_img;
	private String start_date;
	private String end_date;
	private int cert_count;
	private int max_people;
	private String example_img;
	private int join_deposit;
	private int like_cnt;
	private String writer;
	
	public ChallengeDto() {
		super();
	}

	public ChallengeDto(int id, int category_id, int user_id, String title, String content, String sum_img, String start_date, String end_date, int cert_count, int max_people, String example_img, int join_deposit, int like_cnt, String writer) {
		super();
		this.id = id;
		this.category_id = category_id;
		this.user_id = user_id;
		this.title = title;
		this.content = content;
		this.sum_img = sum_img;
		this.start_date = start_date;
		this.end_date = end_date;
		this.cert_count = cert_count;
		this.max_people = max_people;
		this.example_img = example_img;
		this.join_deposit = join_deposit;
		this.like_cnt = like_cnt;
		this.writer = writer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSum_img() {
		return sum_img;
	}

	public void setSum_img(String sum_img) {
		this.sum_img = sum_img;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public int getCert_count() {
		return cert_count;
	}

	public void setCert_count(int cert_count) {
		this.cert_count = cert_count;
	}

	public int getMax_people() {
		return max_people;
	}

	public void setMax_people(int max_people) {
		this.max_people = max_people;
	}

	public String getExample_img() {
		return example_img;
	}

	public void setExample_img(String example_img) {
		this.example_img = example_img;
	}

	public int getJoin_deposit() {
		return join_deposit;
	}

	public void setJoin_deposit(int join_deposit) {
		this.join_deposit = join_deposit;
	}

	public int getLike_cnt() {
		return like_cnt;
	}

	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	@Override
	public String toString() {
		return "ChallengeDto{" +
				"id=" + id +
				", category_id=" + category_id +
				", user_id=" + user_id +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", sum_img='" + sum_img + '\'' +
				", start_date='" + start_date + '\'' +
				", end_date='" + end_date + '\'' +
				", cert_count=" + cert_count +
				", max_people=" + max_people +
				", example_img='" + example_img + '\'' +
				", join_deposit=" + join_deposit +
				", like_cnt=" + like_cnt +
				", writer='" + writer + '\'' +
				'}';
	}
}
