package com.aca.web0810.notice;

import java.io.IOException;
import java.io.PrintWriter;
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
//중의적인 클래스를 이용한 웹용  

import com.aca.web0810.domain.Board;
import com.aca.web0810.model.BoardManager;
public class WriteController extends HttpServlet{
	BoardManager boardManager;
	
	//이 서블릿 클래스가 최초의 접속자에 의해 인스턴스화 될떄 딱 한번 호출되는 생명주기 메서드
	@Override
	public void init() throws ServletException {
		boardManager = new BoardManager();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		request.setCharacterEncoding("utf-8");
		String title 		= request.getParameter("title");
		String writer 	= request.getParameter("writer");
		String content = request.getParameter("content");
		
		//넘어온 파라미터를 이용해서 DB에 insert하되, 직접이 아닌 중립적 객체를 이용할 것임
		Board board = new Board();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		int result = boardManager.insert(board);
		out.print("<script>");
		 if(result==0) {
			 out.print("alret('등록실패');");
			 out.print("history.back();");
		 }else {
			 out.print("alert('등록성공');");
			 out.print("location.href='/notice/list.jsp';");//밑에 문장들도 실행됨
		 }
		 out.print("</script>");
		
	}
}
