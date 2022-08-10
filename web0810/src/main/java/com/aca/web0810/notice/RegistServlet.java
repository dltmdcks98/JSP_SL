package com.aca.web0810.notice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class RegistServlet extends HttpServlet{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//클라이언트가 전송해온 파라미터 받기
		request.setCharacterEncoding("utf-8");
		String title 		= request.getParameter("title");
		String writer 	= request.getParameter("writer");
		String content = request.getParameter("content");
		
		//접속이라는 행위는 내부적으로 상당히 많은 절차와 인증을 거쳐야 하는 어려운 작업
		//DriverManager.getConnection(content)접속

		
		Connection con=null;
		PreparedStatement pstmt = null;
		//자바 클래스 안에 추후변경가능성이 큰 자원의 정보는 기재하지 않는다.(하드코딩X)
		// 검색을 담당하는 검색 객체생성
		try {
			InitialContext ctx = new InitialContext();
			//대입한 소스가 Connection poolling을 구현한 객체
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/myoracle");//매개변수로 겁색어를 대입
			
			//커넥션 풀로부터  Connection 하나를 빌려보기
			 con= ds.getConnection();
			 String sql ="INSERT INTO board(board_id,title,writer,content) VALUES(seq_board.nextval,?,?,?)";
			 pstmt=con.prepareStatement(sql);
			 pstmt.setString(1, title);
			 pstmt.setString(2, writer);
			 pstmt.setString(3, content);
			 
			 pstmt.executeUpdate();//실행
			 
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {}
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
