package com.aca.web0812.news;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aca.web0812.news.model.NewsDAO;

/*
 * 댓글이 있으면 '원본이 삭제되었다'고 수정
 * 댓글이 없으면 그냥 삭wp
 */
public class DeleteServlet extends HttpServlet {
	NewsDAO newsDAO = new NewsDAO();
	
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int news_id = Integer.parseInt(request.getParameter("news_id"));
			
			int result = newsDAO.delete(news_id);
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.print("<script>");
			if(result==0) {
				out.print("alert('삭제실패');");
				out.print("history.back();");
			}else {
				out.print("alert('삭제 성공');");
				out.print("location.href='/news/list.jsp';");
			}
			out.print("</script>");
		}
}
