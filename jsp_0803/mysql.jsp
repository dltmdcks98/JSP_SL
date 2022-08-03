<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>

<%
	//로직 작성
	
	//드라이버 로드 
	Class.forName("com.mysql.jdbc.Driver");

	//접속
	////주소/테이블명
	String url="jdbc:mysql://localhost/javastudy";
	String user = "root";
	String password="1234";
	//아래의 영역에서 이 변수들을 사용하기 위함(if문 밖으로 뺌)
	PreparedStatement pstmt =null;
	ResultSet rs = null;

	Connection con = DriverManager.getConnection(url,user,password);
	if(con==null){
		out.print("실패");
	}else{
		out.print("mysql 성공");
		String sql = "select * from emp";
		pstmt = con.prepareStatement(sql);//쿼리 수행 객체 생성 
		rs = pstmt.executeQuery();
	}
%>

<table width="800px" border="1px">
	<tr>
		<td>empno</td>
		<td>ename</td>
		<td>job</td>
		<td>mgr</td>
		<td>hiredate</td>
		<td>sal</td>
		<td>comm</td>
		<td>deptno</td>
	<tr>
<!-- 	데이터가 실제로 나오는 구간 -->
<%while(rs.next()){%>
	<tr onMouseOver="this.style.background='yellow'" onMouseOut="this.style.background=''"><!--여기서 this는 tr을 가르키고 있다.-->
		<td><%=rs.getInt("empno")%></td><!--out.print()를 =로 바꿀 수 있고, ;를 뺀다.-->
		<td><%out.print(rs.getString("ename"));%></td>
		<td><%out.print(rs.getString("job"));%></td>
		<td><%out.print(rs.getInt("mgr"));%></td>
		<td><%out.print(rs.getString("hiredate"));%></td>
		<td><%out.print(rs.getInt("sal"));%></td>
		<td><%out.print(rs.getInt("comm"));%></td>
		<td><%out.print(rs.getInt("deptno"));%></td>
	<tr>
<%}%>
</table>

<%if(rs!=null)rs.close();	
	if(pstmt!=null)pstmt.close();
	if(con!=null)con.close();
	%>