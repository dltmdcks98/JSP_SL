package com.aca.web0812.news;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aca.web0812.domain.Comments;
import com.aca.web0812.domain.News;
import com.aca.web0812.model.CommentsDAO;
import com.google.gson.Gson;

/*댓글 등록 요청을 처리하는 서블릿*/
public class CommentsRegist extends HttpServlet{
	CommentsDAO commentsDAO = new CommentsDAO();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String detail = request.getParameter("detail");
		String author = request.getParameter("author");
		String news_id = request.getParameter("news_id");
		
		//DTO 
		Comments comments = new Comments();//Empty 객체 생성
		comments.setDetail(detail);
		comments.setAuthor(author);
		
		//객체변수이므로 메모리에 올려야함
		News news = new News();
		news.setNews_id(Integer.parseInt(news_id));
		
		comments.setNews(news);//comments DTO안에 News DTO 넣기 자식이 부모를 has a로 보유
		
		//DAO : DAO는 테이블 별로 1:1 대응
		commentsDAO.insert(comments);
		
		//클라이언트가 비동기 방식으로 요청을 한다는 것은 전체 html 디자인을 바꾸는 것이 아니라 현재 페이지는 유지하되 
		//오직 데이터면 주고 받기 위함
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//이 뉴스기사에 딸려있는 댓글 가져오기
		List<Comments> commentsList = commentsDAO.selectAll(news.getNews_id());
		
		//클라이언트에게 등록과 동시에 지금까지 누적된 댓글 목록을 보낸다.
		//json표기를 문자열로 처리할 경우 너무 번거로우니 외부 라이브러리(GSON)를 이용한다.
		
		//목록 가져오기 
		Gson gson = new Gson();
		String json =gson.toJson(commentsList);
		out.print(json);
	}
}
