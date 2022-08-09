<%@ page contentType="text/html;charset=UTF-8"%>
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
		<%for(int i=1; i<=10; i++){ %>
		<tr>
			<td>No</td>
			<td>제목</td>
			<td>작성자</td>
			<td>작성일</td>
			<td>조회수</td>
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