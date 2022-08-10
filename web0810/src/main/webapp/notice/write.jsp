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
</script>
</head>
<body>

	<h3>Contact Form</h3>

	<div class="container">
		<form name="form1">
			<input type="text" id="fname" name="title"placeholder="제목입력..."> 
			<input type="text" id="lname" name="writer" placeholder="작성자 입력...">
			<textarea id="subject" name="content" placeholder="내용 입력...."	style="height: 200px"></textarea>
			<input type="button" value="Submit" onClick="regist()">
		</form>
	</div>

</body>
</html>
