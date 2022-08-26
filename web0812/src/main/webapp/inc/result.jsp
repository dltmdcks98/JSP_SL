<%@ page contentType="text/html;charset=UTF-8"%>
<%!
	public String getMsgURL(String msg, String url){
		StringBuilder sb= new StringBuilder();
		 sb.append("<script>");
		 sb.append("alert('"+msg+"');");
		 sb.append("location.href='"+url+"';");
		sb.append("</script>");
	
		return sb.toString();
	}
	public String getMsgBack(String msg){
		StringBuilder sb= new StringBuilder();
		 sb.append("<script>");
		 sb.append("alert('"+msg+"');");
		 sb.append("history.back();");
		sb.append("</script>");
	
		return sb.toString();
	}

%>