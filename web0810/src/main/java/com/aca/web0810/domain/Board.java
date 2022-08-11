package com.aca.web0810.domain;

/*로직이 아닌 데이터를 보관하기 위한 용도
 * 즉 Board는 게시물 한건을 보관하기 위함
 * 클래스 명은 테이블 명과 동일하게
 * */
public class Board {
	//DB 테이블의 칼럼명을 동일하게 한다.
	private int board_id;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
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
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
}
