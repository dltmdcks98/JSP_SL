package com.academy.web0829.board.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.academy.web0829.domain.Board;

public class RegistServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 받기 
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Board board = new Board();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		//쿼리문이 들어있는 BoardMapper.xml을 이용
		String resource = "com/academy/web0829/mybatis/config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		
		//mybatis를 이용하면 쿼리문을 수행하기 위한 객체인 sqlSession객체를 이용하여 sql문을 호출할 수 있다.
		SqlSession sqlSession  = factory.openSession();
		int result = sqlSession.insert("babo.insert",board);
		sqlSession.commit();
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("등록 성공");
		
		if(result > 0 ) {
			out.print("성공");
		}else {
			out.print("실패");
		}
	}
}
