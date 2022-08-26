<%@ page contentType="text/html;charset=UTF-8"%>
<%
	int totalRecord =26; //총 레코드 수 
	int pageSize=10;//한 페이지당 보여질 레코드 수
	int totalPage = (int)Math.ceil((float)totalRecord/pageSize);
	int blockSize = 10;//블록당 보여질 페이지 수 
	int currentPage = 1;
	if(request.getParameter("currentPage")!=null){
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	int firstPage=currentPage - (currentPage-1)%blockSize;
	int lastPage=firstPage-(blockSize-1);
	int curPos =(currentPage-1)*pageSize;//페이지당 List 의 시작 index
	int num = totalRecord - curPos;//페이지당 시작 번호
	
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
</style>
<%@ include file="/inc/header.jsp" %>
<script>
	$(document).ready(function(){//addEventListener("load",function)과 같음
		$("button").click(function(){
//			location.href="/reboard/regist.jsp"; JS
			$(location).attr("href","/reboard/regist.jsp");//jquery
		});
	});
</script>
</head>
<body>
	<table>
		<tr>
			<th>No</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>
		<tr>
			<td>Jill</td>
			<td>Smith</td>
			<td>50</td>
			<td>50</td>
			<td>50</td>
		</tr>
		<tr>
			<td colspan="5"><button>글등록</button></td>
		</tr>
	</table>
	<%@ include file="/inc/footer.jsp" %>
</body>
</html>
