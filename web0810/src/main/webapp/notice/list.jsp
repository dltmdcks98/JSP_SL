<%@ page contentType = "text/html;charset=UTF-8" %>
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
<script>
addEventListener("load",function(){
	document.querySelector("button").addEventListener("click", function(){
		location.href="/notice/write.jsp";
	});
});
</script>
</head>
<body>

	<table>
		<tr>
			<th width="5%">No</th>
			<th width ="65%">title</th>
			<th width ="25%">writer</th>
			<th width ="10%">regdate</th>
			<th width="5%">hit</th>
		</tr>
		<%for(int i=1; i<=10; i++){ %>
		<tr>
			<td>Jill</td>
			<td>Smith</td>
			<td>50</td>
			<td>50</td>
			<td>50</td>
		</tr>
		<%} %>
		<tr>
			<td>Eve</td>
			<td>Jackson</td>
			<td>94</td>
			<td>94</td>
			<td>94</td>
		</tr>
		<tr>
			<td colspan="5">
				<button>글등록</button>
			</td>
		</tr>
	</table>

</body>
</html>
