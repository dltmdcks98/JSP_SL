<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table width="200px" border="1px">
		<%for(int i=1; i<=9; i++){ %>
		<tr>
			<td><%="2*"+i+"="+(2*i) %></td>
		</tr>
		<%} %>
	</table>
</body>
</html>