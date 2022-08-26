<%@page import="com.aca.web0812.reboard.model.ReBoardDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! ReBoardDAO reBoardDAO = new ReBoardDAO();%>
<jsp:useBean id="reBoard" class="com.aca.web0812.reboard.domain.ReBoard"/>
<%request.setCharacterEncoding("utf-8"); //빈즈태그로 채우기 전에 인코딩 처리 %>
<jsp:setProperty property="*" name="reBoard"/>
<%@ include file="/inc/result.jsp" %>
<%
	//답변 등록에 필요한 파라미터들을 받아와 등록
 	int result =	reBoardDAO.reply(reBoard);
	if(result==0){
		out.print(getMsgBack("답변 등록실패"));
	}else{
		out.print(getMsgURL("답볍 등록 성공","/reboard/list.jsp"));
	}
%>