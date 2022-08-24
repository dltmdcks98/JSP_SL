package com.aca.web0812.news;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.aca.web0812.domain.News;
import com.aca.web0812.news.model.NewsDAO;


/*
 * 뉴스기사 등록 요청을 처리하는 서블릿
 */
public class RegistServlet extends HttpServlet{
	NewsDAO newsDAO = new NewsDAO();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//날아온 파라미터를 받아서, db에 넣기
		request.setCharacterEncoding("utf-8");
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		
		//DTO 담기
		News news = new News();
		news.setTitle(title);
		news.setWriter(writer);
		news.setContent(content);
		
		int result = newsDAO.insert(news);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out= response.getWriter();
		out.print("<script>");
		if(result==0) {
			out.print("alert('등록 실패');");
			out.print("history.back();");			
		}else {
			out.print("alert('등록 성공');");
			out.print("location.href='/news/list.jsp'");
		}
		out.print("</script>");
	}
}