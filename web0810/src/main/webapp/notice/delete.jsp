<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.aca.web0810.domain.Board"%>
<%@page import="com.aca.web0810.model.BoardDAO"%>
<%! BoardDAO boardDAO = new BoardDAO();  %>
<%
	String board_id = request.getParameter("board_id");
	out.print(board_id);
	
	int result = boardDAO.delete(Integer.parseInt(board_id));
	out.print("<script>");
	if(result==0){
		out.print("alert('삭제 실패');");
		out.print("history.back();");
	}else{
		out.print("alert('삭제성공');");
		out.print("location.href='/notice/list.jsp';");
	}
	out.print("</script>");
	
%>