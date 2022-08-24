package com.aca.web0812.news;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aca.web0812.domain.Comments;

/*댓글 등록 요청을 처리하는 서블릿*/
public class CommentsRegist extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String detail = request.getParameter("detail");
		String author = request.getParameter("author");
		
		//DTO 
		Comments comments = new Comments();//Empty 객체 생성
		comments.setDetail(detail);
		comments.setAuthor(author);
		
		//DAO : DAO는 테이블 별로 1:1 대응
	}
}
