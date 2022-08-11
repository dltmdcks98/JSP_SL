<%@page import="com.aca.web0810.model.BoardDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<%!BoardDAO boardDAO = new BoardDAO();%>
<%request.setCharacterEncoding("utf-8"); %>
<!-- JSP에서 태그이지만, 오직 서버에서만 실행될수 있는 빈즈태그를 지원한다. useBean = new 의 의미와 같음-->
<jsp:useBean id="board" class="com.aca.web0810.domain.Board" />
<jsp:setProperty property="*" name="board"/><!-- board에 html과 일치하는 모든 파라미터를 삽입 -->
<%
/*넘겨받은 파라미터들을 이용하여 DB에 Update*/
	int result = boardDAO.update(board);
out.print("<script>");
if(result==0){
	out.print("alert('수정 실패');");
	out.print("history.back();");
}else{
	out.print("alert('수정 성공');");
	out.print("location.href='/notice/list.jsp';");
}
out.print("</script>");
%>