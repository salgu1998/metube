package com.metube.comment.vo;

public class commentVO {
	private int pk;
	private int post_pk;
	private int user_pk;
	private String content;
	
	public int getPk() {
		return pk;
	}
	public void setPk(int pk) {
		this.pk = pk;
	}
	public int getPost_pk() {
		return post_pk;
	}
	public void setPost_pk(int post_pk) {
		this.post_pk = post_pk;
	}
	public int getUser_pk() {
		return user_pk;
	}
	public void setUser_pk(int user_pk) {
		this.user_pk = user_pk;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}