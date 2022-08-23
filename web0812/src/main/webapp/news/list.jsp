<%@ page contentType="text/html;charset=UTF-8"%>
<%
	int totalRecord = 26;//모든 레코드 수 
	int pageSize = 10; //한 페이지당 보여질 레코드 수 
	int totalPage = (int)Math.ceil((float)totalRecord/pageSize); 
	int blockSize = 10;//한 블럭당 보여질 페이지 수 
	int currentPage =Integer.parseInt(request.getParameter("currentPage")); //현재 페이지 
%>
<%="totalRecord는 " + totalRecord+"<br>" %>
<%="pageSize는 " + pageSize+"<br>" %>
<%="totalPage " + totalPage+"<br>" %>
<%="blockSize " + blockSize+"<br>" %>
<%="currentPage " + currentPage+"<br>" %>
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
</style>
</head>
<body>
	<table>
		<tr>
			<th width="5%">No</th>
			<th width="70%">기사 제목</th>
			<th width="5%">작성자</th>
			<th width="10%">작성일</th>
			<th width="5%">조회수</th>
		</tr>
		<!-- 하나의 페이지에 너무 많은 데이터가 있을 경우, 원하는 크기로 분리하여 보여주는 기법을 페이징(Paging)처리라고 하는데
		이는 데이터에 대한 산수계산 이므로 개발자가 로직을 개발해야함 -->
		<%for(int i=1;i<=pageSize;i++){ %>
		<tr>
			<td>Jill</td>
			<td>Smith</td>
			<td>50</td>
			<td>50</td>
			<td>50</td>
		</tr>
		<%} %>
		<tr>
			<td colspan="5" style="text-align:center">
				◀
				<%for(int i=1; i<=blockSize; i++){ %>
					<a href="/news/list.jsp?currentPage=<%=i%>">[<%= i %>]</a>
					
				<%currentPage= i;} %>
				▶
			</td>
		</tr>
	</table>

</body>
</html>
