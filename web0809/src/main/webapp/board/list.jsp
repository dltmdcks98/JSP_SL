<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! 
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "java";
	String password = "1234";
		
	%>
<%
	//이 jsp가 톰켓에 의해 서블릿 클래스로 전환될때 service메소드 의 영역
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con = DriverManager.getConnection(url,user,password);
	
	String sql="SELECT * FROM board ORDER BY board_id DESC";
	PreparedStatement pstmt = con.prepareStatement(sql);//준비
	ResultSet rs = pstmt.executeQuery();//select수행

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
<script>

</script>
<style>

</style>
</head>
<body>
	<table width="100%" border="1px" align="center">
		<tr>
			<td>No</td>
			<td>제목</td>
			<td>작성자</td>
			<td>작성일</td>
			<td>조회수</td>
		</tr>
		<%while(rs.next()){ %>
		<tr>
		<!-- no에 primary키는 쓰지 않음 이빨 빠짐 순서가 이상하게 설정됨 -->
			<td>No</td>
			<td><%=rs.getString("title")%></td>
			<td><%=rs.getString("writer")%></td>
			<td><%=rs.getString("regdate")%></td>
			<td><%=rs.getInt("hit")%></td>
		</tr>
		<%} %>
		<tr>
			<td colspan="5" align="right">
			<!-- 하나의 문서 내에서 생성되는 객체를 DOM(Document Object Model)이라 하고, 문서영역에서 브라우저를 위해
			 생성되는 객체들을 가리켜 비공식적으로 BOM(Browser Object Model) 이라고 한다. -->
				<button onClick="location.href='/board/regist.jsp'">글 등록</button>
			</td>
		</tr>
	</table>	
</body>
</html>
<%
if(rs!=null)rs.close();
if(pstmt!=null)pstmt.close();
if(con!=null)con.close();
%>