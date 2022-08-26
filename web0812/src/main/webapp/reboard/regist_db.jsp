<%@page import="com.aca.web0812.reboard.model.ReBoardDAO"%>
<%@page import="com.aca.web0812.reboard.domain.ReBoard"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! 
	ReBoardDAO reBoardDAO = new ReBoardDAO(); 
	
	public String getMsgURL(String msg, String url){
		StringBuilder sb= new StringBuilder();
		 sb.append("<script>");
		 sb.append("alert("+msg+");");
		 sb.append("location.href="+url+";");
		sb.append("</script>");
	
		return sb.toString();
	}
	public String getMsgBack(String msg){
		StringBuilder sb= new StringBuilder();
		 sb.append("<script>");
		 sb.append("alert("+msg+");");
		 sb.append("history.back();");
		sb.append("</script>");
	
		return sb.toString();
	}
%>
<%
	//글쓰기 요청을 처리하는 jsp
	request.setCharacterEncoding("utf-8");
	String title = request.getParameter("title");
	String writer = request.getParameter("writer");
	String content = request.getParameter("content");

	//DTO에 채우기
	ReBoard reBoard = new ReBoard();
	reBoard.setTitle(title);
	reBoard.setWriter(writer);
	reBoard.setContent(content);
	
	//insert 시키기 
	int result = reBoardDAO.insert(reBoard);
	
	if(result==0){
		out.print(getMsgBack("등록실패"));
	}else{
		out.print(getMsgURL("등록성공","/reboard/list.jsp"));
	}
%>