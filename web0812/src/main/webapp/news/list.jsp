<%@page import="javax.print.attribute.HashPrintRequestAttributeSet"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	int totalRecord=80;  //모든 레코드 수
	int pageSize=7;  //한 페이지당 보여질 레코드 수
	int totalPage=(int)Math.ceil((float)totalRecord/pageSize);
	int blockSize=7;  //한 블럭당 보여질 페이지 수
	int currentPage=1;
	if(request.getParameter("currentPage")!=null){
		currentPage=Integer.parseInt(request.getParameter("currentPage"));
	}
	int firstPage=blockSize*(int)(currentPage/(float)(blockSize+(float)0.001))+1;
	int lastPage=firstPage+blockSize-1;
	int num=totalRecord-pageSize*(currentPage-1);
%>
<%="totalRecord는 "+totalRecord+"<br>" %>
<%="pageSize는 "+pageSize+"<br>" %>
<%="totalPage는 "+totalPage+"<br>" %>
<%="blockSize는 "+blockSize+"<br>" %>
<%="currentPage는 "+currentPage+"<br>" %>
<%="firstPage는 "+firstPage+"<br>" %>
<%="lastPage는 "+lastPage+"<br>" %>
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
			<th width="75%">기사제목</th>
			<th width="5%">작성자</th>
			<th width="10%">작성일</th>
			<th width="5%">조회수</th>
		</tr>
		<!-- 하나의 페이지에 너무 많은 데이터가 있을 경우, 원하는 크기로 분리하여 보여주는 기법을 페이징(Paging) 처리라
		페이징 처리는 결국 데이터에 대한 산수계산이므로, 개발자마다 본인 스스로 로직을 개발해야함 -->
		<%for(int i =1; i<=pageSize; i++){%>
		<%if(num<1)break; %>
		<tr>
			<td><%=num--%></td>
			<td>Smith</td>
			<td>50</td>
			<td>50</td>
			<td>50</td>
		</tr>
		<%} %>
		<tr>
			<td colspan="5" style="text-align:center">
				<%if(firstPage-1>0){ %>
				<a href="/news/list.jsp?currentPage=<%=firstPage-1%>">◀</a>
				<%}else{ %>
				<a href="javascript:alert('이전 페이지가 없습니다');">◀</a>
				<%} %>
				<%for(int i=firstPage; i<=lastPage; i++) {%>
					<%if(i>totalPage)break; %>
					<a href="/news/list.jsp?currentPage=<%=i%>"<%if(i==currentPage){ %> class="page-style"<%} %>>[<%=i%>]</a>
				<%} %>
				<%if(lastPage+1<=totalPage){ %>
				<a href="/news/list.jsp?currentPage=<%=lastPage+1%>">▶</a>
				<%}else{ %>
				<a href="javascript:alert('마지막 페이지입니다');">▶</a>
				<%} %>
			</td>
		</tr>
		<tr>
			<td colspan="5"><button onClick="location.href='/news/regist.jsp';">뉴스작성</button></td>
		</tr>
	</table>

</body>
</html>