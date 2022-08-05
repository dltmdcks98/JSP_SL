<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import ="com.oreilly.servlet.MultipartRequest" %>

<%
//클라이언트가 보낸 파일을 서버에 업로드 처리하려면, 많은 로직을 수반되므로 누군가 만들어놓은 라이브러리를 이용한다,cos.jar
//cos.jar의 MultipartRequest클래스가 업로드를 처리하는 클래스
	String saveDir="D:/LSC/JSP_SL/jsp0805/data";//저장경로
	MultipartRequest multi = new MultipartRequest(request,saveDir);

	//클라이언트의 브라우저에서 전송한 파라미터(전송되어온 변수)들을 받자
	request.setCharacterEncoding("utf-8");
	String title = request.getParameter("title");
	String spot = request.getParameter("spot");
	String detail = request.getParameter("detail");
	//myfile은 String이 아님getParameter는 글자만 받는다.
		
	

%>