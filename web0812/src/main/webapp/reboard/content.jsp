<%@ page contentType="text/html;charset=UTF-8"%>
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

input[type=button]:hover {
	background-color: #45a049;
}

.container {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}

#comments-list {
	border: 1px solid red;
	overflow: hidden;
}

#comments-list * {
	float: left;
}

.title-style {
	width: 80%
}

.writer-style {
	width: 10%
}

.regdate-style {
	width: 10%
}
</style>
<%@ include file="/inc/header.jsp" %>
</head>
<body>

	<h3>뉴스기사 상세보기</h3>

	<div class="container">
		<form name="form1">
			<input type="text" name="title" value="<%%>"> <input
				type="text" name="writer" value="<%%>">
			<textarea name="content" style="height: 200px">
				<%%>
			</textarea>

			<input type="button" value="답변등록"> <input type="button"
				value="목록""> <input type="button" value="수정"> <input
				type="button" value="삭제">

		</form>

	</div>
	<%@ include file="/inc/footer.jsp" %>
</body>
</html>