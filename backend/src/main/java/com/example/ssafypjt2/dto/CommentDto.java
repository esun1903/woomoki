package com.example.ssafypjt2.dto;

public class CommentDto {
    private int id;
    private int cert_id;
    private int user_id;
    private String content;
    private String create_date;
    private int like_cnt;

    public CommentDto() {
        super();
    }

    public CommentDto(int id, int cert_id, int user_id, String content, String create_date, int like_cnt) {
        super();
        this.id = id;
        this.cert_id = cert_id;
        this.user_id = user_id;
        this.content = content;
        this.create_date = create_date;
        this.like_cnt = like_cnt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCert_id() {
        return cert_id;
    }

    public void setCert_id(int cert_id) {
        this.cert_id = cert_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public int getLike_cnt() {
        return like_cnt;
    }

    public void setLike_cnt(int like_cnt) {
        this.like_cnt = like_cnt;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "id=" + id +
                ", cert_id=" + cert_id +
                ", user_id=" + user_id +
                ", content='" + content + '\'' +
                ", create_date='" + create_date + '\'' +
                ", like_cnt=" + like_cnt +
                '}';
    }
}
