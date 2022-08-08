package web0808.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	고양이만 서블릿을 작성할 수 있는것이 아니라 개발자도 작성할 수 있다.
	extends :  Is A  관계 
	1)상속 : 부모의 자원을 마치 내것 처럼 접근할 수 있는 것
	2)자료형 : 부모와 같은 자료형이 되는 것 
*/
public class MyServlet extends HttpServlet {//MyServlet은 서버에서 실행 될 수 있는 서블릿이다.
	
	@Override
	//클라이언트가 아무것도 명시하지 않고 요청을 할 경우 default는 GET방식으로 들어오게 되는데 GET방식의 
	//요청을 받는 서블릿의 메서드가 아래와 같은  doGet()메서드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//jsp에서는 지시영역에서 작성하였지만, 우리가 정의한 서블릿에서는 response 객체로 처리=> 한글 처리 
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out =response.getWriter();//응답정보 객체로 부터 출력스트림을 얻는다.
		//문자 기반의 출력 스트림
		out.print("안녕, 나의 서블릿에 의한 응답정보");//<%="안녕, 나의 서블릿에 의한 응답정보"%>와 같음
		
	}

}
