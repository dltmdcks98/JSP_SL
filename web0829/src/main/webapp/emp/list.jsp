<%@page import="org.apache.jasper.EmbeddedServletOptions"%>
<%@page import="com.academy.web0829.domain.Dept"%>
<%@page import="com.academy.web0829.domain.Emp"%>
<%@page import="java.util.List"%>
<%@page import="com.academy.web0829.emp.repository.EmpDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! EmpDAO empDAO = new EmpDAO(); %>
<%
	List <Emp> empList = empDAO.selectAll();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table width="100%" border ="1px">
		<tr>
			<td>empno</td>
			<td>ename</td>
			<td>job</td>
			<td>mgr</td>
			<td>hiredate</td>
			<td>sal</td>
			<td>comm</td>
			<td>deptno</td>
			<td>dname</td>
			<td>loc</td>
		</tr>
		<%for(int i=0; i<empList.size();i++){ %>
		<%Emp emp = empList.get(i); %>
		<tr>
			<td><%=emp.getEmpno()%></td>
			<td><%=emp.getEname()%></td>
			<td><%=emp.getJob()%></td>
			<td><%=emp.getMgr()%></td>
			<td><%=emp.getHiredate()%></td>
			<td><%=emp.getSal()%></td>
			<td><%=emp.getComm()%></td>
			<%Dept dept = emp.getDept(); %>
			<td><%=dept.getDeptno()%></td>
			<td><%=dept.getDname()%></td>
			<td><%=dept.getLoc()%></td>
		</tr>
		<%} %>
	</table>
</body>
</html>