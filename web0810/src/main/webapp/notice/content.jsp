<%@page import="com.aca.web0810.domain.Board"%>
<%@page import="com.aca.web0810.model.BoardDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%!
	//선언부는 서블릿의 멤버영역
	BoardDAO boardDAO = new BoardDAO();
%>
<%
	//클라이언트가 전송한 파라미터 board_id 받고 BoardDAO를 이용하여 1건 가져오기
	String board_id = request.getParameter("board_id");
	out.print(boardDAO);
	Board board = boardDAO.select(Integer.parseInt(board_id));
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box;
}

input[type=text], select, textarea {
	width: 100%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 6px;
	margin-bottom: 16px;
	resize: vertical;
}

input[type=button] {
	background-color: #04AA6D;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=submit]:hover {
	background-color: #45a049;
}

.container {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}
</style>
<script>
//입력양식 데이터를 서버에 전송
function regist(){
	form1.action="/notice/regist";
	form1.method="post";
	form1.submit();//전송 발생
}
function del() {
	//삭제 요청
	if(confirm("삭제하시겠습니까?")){
		location.href="/notice/delete.jsp?board_id=<%=board.getBoard_id()%>";	
	}
	
}
function edit(){
	//수정 요청
	if(confirm("수정하겠습니까?")){
		form1.action="/notice/edit.jsp";
		form1.method="post";
		form1.submit();
	}
}
</script>
</head>
<body>

	<h3>Contact Form</h3>

	<div class="container">
		<form name="form1">
			<input type = "hidden" name ="board_id" value="<%=board.getBoard_id() %>">
			<input type="text" id="fname" name="title" value="<%=board.getTitle()%>"> 
			<input type="text" id="lname" name="writer" value="<%=board.getWriter()%>">
			<textarea id="subject" name="content" placeholder="내용 입력...."	style="height: 200px"><%=board.getContent()%>"</textarea>
			<input type="button" value="수정" onClick="edit();">
			<input type="button" value="삭제" onClick="del();">
			<input type="button" value="목록" onClick="location.href='/notice/list.jsp'">
		</form>
	</div>

</body>
</html>
