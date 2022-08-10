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

	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con = DriverManager.getConnection(url,user,password);
	
	int board_id=Integer.parseInt(request.getParameter("board_id"));
	String sql = "SELECT * FROM board WHERE board_id="+board_id;
	
	PreparedStatement pstmt = con.prepareStatement(sql);
	ResultSet rs = pstmt.executeQuery();
	rs.next();//커서 한칸이동
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글등록</title>
<style type="text/css">

</style>
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<script>
 $(document).ready(function() {
		 $('#summernote').summernote();
		 
		 
		 $("#bt_regist").click(function(){
			//폼양식으로 이용하여 서버에 전송
			$("form").attr("action","/board/regist");
			$("form").attr("method","post");
			$("form").submit();
			
		 });
	});
 function edit(){
	 //수정 폼을 서버에 전송 
	 form1.action="/board/edit";
	 form1.method="post";
	 form1.submit();
 }
 function del(){
	 //get 방식으로 삭제를 요청
	 location.href="/board/delete?board_id=<%=board_id%>";
 }
</script>
</head>
<body style="background:yellow">
	<form name = "form1">
	<input type ="hidden" name ="board_id" value = "<%=rs.getInt("board_id") %>">
	<table width="60%" border="1px" align="center">
		<tr>
			<td>제목</td>
			<td><input type ="text" name ="title" value="<%= rs.getString("title")%>"></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><input type ="text" name ="writer" value="<%= rs.getString("writer")%>"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea id="summernote" name="content" ><%= rs.getString("content")%></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="글수정" id="bt_edit" onClick="edit()">
				<input type="button" value="글삭제" id="bt_del" onClick="del()">
				<input type="button" value="목록보기" onClick="location.href='/board/list.jsp'">
			</td>
		</tr>
	</table>
	</form>
</body>
</html>
<%
if(rs!=null)rs.close();
if(pstmt!=null)pstmt.close();
if(con!=null)con.close();
%>
