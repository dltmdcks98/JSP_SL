<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%!
	String url="jdbc:oracle:thin:@localhost:1521:XE"; 
	String user="java";
	String password="1234";%>
<%
	String user_id = request.getParameter("user_id");
	String pass = request.getParameter("pass");
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	con = DriverManager.getConnection(url,user,password);
	String sql = "SELECT * FROM member WHERE user_id=? AND pass=?";
	pstmt = con.prepareStatement(sql);
	pstmt.setString(1, user_id);
	pstmt.setString(2, pass);
	
	rs=pstmt.executeQuery();

	/*
	클라이언트가 전송한 아이디,패스워드를 DB에서 조회하여 만일 가입된 회원이라면 
		서버가 추후 다시 접속해도 나를 기억
		
		JSP의 내장객체중 session이라는 객체가 바로 세션을 구성
		최초의 접속자가 들어오면 세션객체를 생성한 후 고유 ID를 할당하고, 이 ID를 응답정보에 심어놓는다.(쿠키)
		다음 클라이언트 방문시 ID가 존재한다면 아는체 하고 없다면 새로운 세션을 만들고 ID발급
		
		*/
		String sid = session.getId();
		out.print("당신이 할당받은 세션 아이디 :"+sid);
		
	if(rs.next()){//커서를 내렸을때 다음 레코드가 있다면
		//세션 인트턴스에는 개발자가 넣고 싶은 데이터를 넣을 수 있는데 map구조 되어있다.
		/*
			java collection framework : java의 자료구조 java.util 패키지에 있음
			1. list : 순서가 있는 데이터의 모임
			2. set : 순서가 없는 데이터의 모임 
			3. map : key-value 형태로 되어있는 데이터 
		
		*/ 
		session.setAttribute("user_id", rs.getString("user_id"));
		session.setAttribute("pass", rs.getString("pass"));
		session.setAttribute("regdate",rs.getString("regdate"));
		out.print("당신의 정보 <br>");
		out.print("아이디 :" + session.getAttribute("user_id")+"<br>");
		out.print("비번 :" + session.getAttribute("pass")+"<br>");
		out.print("가입일 :" + session.getAttribute("regdate")+"<br>");
	}else{
		out.print("<script>");
		out.print("alert('회원정보가 올바르지 않습니다.');");
		out.print("history.back();");
		out.print("</script>");
	}

	
//자원해제
if(rs!=null)rs.close();	
if(pstmt!=null)rs.close();	
if(con!=null)rs.close();	
	
%>