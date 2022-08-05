<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import ="com.oreilly.servlet.MultipartRequest" %>
<%@ page import ="java.sql.*" %>
<%!															//테이블명
	String url ="jdbc:mysql://localhost:3306/javastudy";
	String user="root";
	String password="1234";
%>
<%
//클라이언트가 보낸 파일을 서버에 업로드 처리하려면, 많은 로직을 수반되므로 누군가 만들어놓은 라이브러리를 이용한다,cos.jar
//cos.jar의 MultipartRequest클래스가 업로드를 처리하는 클래스
//	String saveDir="D:/LSC/JSP_SL/jsp0805/data";//저장경로 \ 가 아니라 /로 해야함 => 절대경로 방식
/*위와 같이 이미지가 업로드될 경로를 해당 os플랫폼에 맞게 고정시켜 버리면 다른 플랫폼기만에서는 
어플리케이션을 수정해야하므로 위와 같은 하드코딩은 안하는 것이 좋다. 해결책은 jsp내장 객체중 현재 웹 어플리케이션의 전반적인 정보를 다루는 객체인
application 객체를 이용*/
	String saveDir=application.getRealPath("/data");
	out.print("data 디렉토리의 실제 위치"+saveDir+"<br>");
	int maxSize=1024*1024*3;//3M
	String encoding="utf-8";
	MultipartRequest multi = new MultipartRequest(request,saveDir,maxSize,encoding);

	//클라이언트의 브라우저에서 전송한 파라미터(전송되어온 변수)들을 받자
/*	request.setCharacterEncoding("utf-8");
	String title = request.getParameter("title");
	String spot = request.getParameter("spot");
	String detail = request.getParameter("detail");
	//myfile은 String이 아님getParameter는 글자만 받는다.*/
//	out.print("title");하면 null로 나오는데 form을 multipart로 처리 되서 그럼 이후에는 아래와 같이 입력해야함

	String title = multi.getParameter("title");
	String spot = multi.getParameter("spot");
	String detail = multi.getParameter("detail");	
	
	//파일의 이름을 아직 바꾸지 않았으므로 insert를 먼저 진행한 후 그 쿼리로부터 생성된 레코드의 primary key를 이용하여 파일명을 사용
	/*1)드라이버 로드 2) 접속 3)쿼리 수행 4)자원해제*/ 
	//1)드라이버 로드
	Class.forName("com.mysql.jdbc.Driver");
	//2)접속
	Connection con = DriverManager.getConnection(url,user,password);
	if(con==null){
		out.print("연결 실패");
	}else out.print("성공");

	if(con!=null)con.close();
%>