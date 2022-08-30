<%@page import="com.academy.web0829.domain.Board"%>
<%@page import="com.academy.web0829.util.Pager"%>
<%@page import="java.util.List"%>
<%@page import="com.academy.web0829.board.repository.BoardDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! 
	BoardDAO boardDAO = new BoardDAO(); 
	Pager pager = new Pager();
	%>
<%
	List <Board> boardList = boardDAO.selectAll();
	out.print("게시물 수는 :"+boardList.size());
	pager.init(boardList, request);//공식 계산 
	
	//jsp의 내장 객체 중 application 내장 객체의 생명력을 테스트한다.
	//이름 그대로 application(웹 어플리케이션)은 Tomcat 서버를 가동할때 생성되어 
	//프로그램이 끝날때 까지 즉 Tomcat 서버를 종료할때까지 생명력을 유지할 수 있다. 
	//위의 설명이 Application scope
	application.setAttribute("id", "batman");
	out.print(application.getAttribute("id"));
	out.print(application.getAttribute("nick"));
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

.page-style{
	font-size:20px;
	font-weight:bold;
	color:red;
}
</style>
</head>
<body>

	<table>
		<tr>
			<th width="5%">No</th>
			<th width="75%">제목</th>
			<th width="5%">작성자</th>
			<th width="10%">작성일</th>
			<th width="5%">조회수</th>
		</tr>
		<%
			int curPos = pager.getCurPos();
			int num = pager.getNum();
		%>
		<%=pager.getPageSize() %>
	<%for(int i=1; i<=pager.getPageSize(); i++){ %>
	<% if(num<1)break; %>
	<%Board board =boardList.get(curPos++); %>
		<tr>
			<td><%=num-- %></td>
			<td><a href="/board/content.jsp?board_id=<%=board.getBoard_id() %>"><%=board.getTitle() %></a></td>
			<td><%=board.getWriter() %></td>
			<td><%=board.getRegdate() %></td>
			<td><%=board.getHit() %></td>
		</tr>
	<%} %>
		<tr>
			<td colspan="5" style="text-align:center">
			<%for(int i = pager.getFirstPage();i<=pager.getLastPage();i++){ %>
			<%if(i>pager.getTotalPage())break; %>
				[<%=i %>]
				<%} %>
			</td>
		</tr>
		<tr>
			<td colspan="5"><button onClick="location.href='/board/regist.jsp';">글작성</button></td>
		</tr>
	</table>

</body>
</html>