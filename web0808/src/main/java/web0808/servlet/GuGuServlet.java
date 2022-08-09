package web0808.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GuGuServlet extends HttpServlet {
	
	//Post등을 명시하지 않는한 기본적으로 브라우저의 요청은 디폴트가 GET방식이고 GET방식으로 들어온 요청은 doGet()메소드가 처리 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =  response.getWriter();//문자기반의 출력 스트림
		out.print("<table  width=\"200px\" border=\"1px\">");
		for(int i=1; i <=9; i++) {
			out.print("<tr>");
			out.print("<td> 5*"+i+"="+(5*i)+"</td>");
			out.print("</tr>");
		}
		out.print("</table>");
	}
}
