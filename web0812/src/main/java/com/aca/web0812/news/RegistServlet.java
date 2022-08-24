package com.aca.web0812.news;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


/*
 * 뉴스기사 등록 요청을 처리하는 서블릿
 */
public class RegistServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//날아온 파라미터를 받아서, db에 넣기
		request.setCharacterEncoding("utf-8");
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			InitialContext context=new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/myoracle");//JNDI 검색 객체
			
			//커넥션풀로부터 Connection 하나를 추출해보자
			con = ds.getConnection();
			String sql="insert into news(news_id,title, writer, content) values (seq_news.nextval,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, content);
			
			int result=pstmt.executeUpdate();//쿼리수행
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}