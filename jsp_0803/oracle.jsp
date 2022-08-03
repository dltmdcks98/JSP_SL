<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
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
		out.print("성공<br>");	
		
		//쿼리문
		PreparedStatement pstmt=null;//인터페이스
		String sql="SELECT * FROM dept";
		pstmt = con.prepareStatement(sql);//(SQL문)이 시점에 메모리에 올라옴 
		//resultSet 으로 반환 => oracle표를 통으로 넣을 수 있는 객체 ,처음에는 커서가 밖에 빠져있는데 커서를 이동해야 행단위로 조작이 가능
		ResultSet rs = pstmt.executeQuery();//쿼리문 실행 
		//커서 조작
		rs.next();//커서 한칸 전진
		rs.next();
		rs.next();
		rs.next();
		String loc = rs.getString("loc");
		out.print(loc);
	}

		
	%>
</body>
</html>
