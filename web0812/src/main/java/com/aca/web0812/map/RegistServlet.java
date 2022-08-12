package com.aca.web0812.map;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//클라이언트의 등록 요청을 처리하는 서블릿
public class RegistServlet extends HttpServlet {
	/*클라이언트가 비동기 요청을 하면 화면전체를 바꾸려는 것이 아니라 데이터만을 교환하기 위함이므로 기존 동기방식처럼 html 페이지 전체를 보내지 말고,
	 * 데이터만을 응답정보로 보내는게 올바르다.*/

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 받기
		request.setCharacterEncoding("utf-8");
		String lati = request.getParameter("lati");
		String longi = request.getParameter("longi");
		String icon = request.getParameter("icon");
		String content = request.getParameter("content");
		
		System.out.println("lati"+lati);
		System.out.println("longi"+longi);
		System.out.println("icon"+icon);
		System.out.println("content"+content);

		//응답을 html로 보내지 마록, insert한 결과가 성공인지 실패인지 여부 메시지
		//즉 복합된 결과를 보내고자할때는 xml이나 json이 많이 쓰임
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out= response.getWriter();
		
		//성공여부 코드, 메시지
		
		//out.print() 안에 인수로 넣은 스트링은 json 오브젝트는 아니고 그냥 json 표기법을 지킨 String 따라서 클라이언트는 파싱해서 사용해야함
		String resData ="{";
		resData+="\"code\":1,";
		resData+="\"msg\":\"데이터 등록 성공\"";
		resData+="}";
		
		out.print(resData);
	}
}
