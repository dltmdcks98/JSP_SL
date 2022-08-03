<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%
	//로직 작성
	
	//드라이버 로드 
	Class.forName("com.mysql.jdbc.Driver");

	//접속
	////주소/테이블명
	String url="jdbc:mysql://localhost/javastudy";
	String user = "root";
	String password="1234";
	Connection con = DriverManager.getConnection(url,user,password);
	if(con==null){
		out.print("실패");
	}else{
		out.print("mysql 성공");
	}
%>