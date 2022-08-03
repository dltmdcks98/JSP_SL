<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Document</title>
</head>
<body>
	<%
	//자바 스탠다드의 문법이 적용되므로, 오라클을 연동해보자
	//jdbc:제품명:연결방식@주소
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "java";
	String password="1234";

	//방금 가져온 드라이버를 메모리에 로드
	Class.forName("oracle.jdbc.driver.OracleDriver");

	//접속시도 DriverManager 에서 반환이 Connection 으로됨  Connection은 접속을 했으면 값을 받을 수 있는 객체일 뿐 
	Connection con = DriverManager.getConnection(url,user,password);  
	
	if(con==null){
		out.print("접속실패");
	}else{
		out.print("성공");	
	}
		
	%>
</body>
</html>
