package com.aca.web0812.news.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/*
 * 기존 코드방식에서랑은 다르게, Connection 객체는 커넥션 풀을 이용한다
 * 우리가 사용하는 커넥션풀은 Tomcat 서버가 제공하는 풀을 이용하되, JNDI로 자원에 접근할 예정
 */
public class NewsDAO {
	InitialContext context;
	DataSource ds;
	
	public NewsDAO(){
		try {
			context= new InitialContext();
			ds=(DataSource)context.lookup("java.comp/env/java/myoracle"); //pool 얻기
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Create
	public void insert() {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ds.getConnection();
			String sql="insert into news(news_id, title, writer, content) values(seq_news.nextval,?,?,?)";
			pstmt=con.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	//Read
	public void selectAll() {
		String sql="select * from news order by news_id desc";
	}
	public void select() {
		String sql="select * from news where news_id=?";
	}
	//update
	public void update() {
		String sql="update news set title=?, writer?, content=? where news_id=?";
	}
	//delete
	public void delete() {
		String sql="delete news where news_id=?";
	}
}