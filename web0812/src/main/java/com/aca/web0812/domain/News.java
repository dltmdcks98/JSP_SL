package com.aca.web0812.domain;

public class News {
	private int News_id;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	public int getNews_id() {
		return News_id;
	}
	public void setNews_id(int news_id) {
		News_id = news_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
}
