<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

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
</style>
<script>
function regist(){
	form1.action="/board/regist";
	form1.method="post";
	form1.submit();
}
</script>
</head>
<body>

<h3>Contact Form</h3>

<div class="container">
  <form name="form1">
    <input type="text" name="title" placeholder="제목입력">
    <input type="text"name="writer" placeholder="작성자 입력">
    <textarea name="content" placeholder="내용작성" style="height:200px"></textarea>
    <input type="button" value="등록" onClick="regist()">
    <input type="button" value="목록" onClick="location.href='/board/list.jsp'">
  </form>
</div>

</body>
</html>