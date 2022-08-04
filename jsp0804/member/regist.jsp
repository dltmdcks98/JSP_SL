<%@ page contentType="text/html;charset=utf-8"%><!--날라오는 문서에 대한 인코딩-->
<%@ page import ="java.sql.*"%>
<%!
	//선언부
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user="java";
	String password="1234";
%>

<%
	//스클립틀릿:로직작성
	//out.print("이jsp에서 회원을 처리");
	//클라이언트인 브라우저가 전송한 파라미터(전송변수 즉 html에서의 name)들을 받기

	request.setCharacterEncoding("utf-8");//전송받는 파라미터값들에 대한 인코딩
	String user_id		= request.getParameter("user_id");
	String pass			= request.getParameter("pass");
	String user_name	= request.getParameter("user_name");
	String mail_id		= request.getParameter("mail_id");
	String myserver		= request.getParameter("myserver");
	String mail_server		= request.getParameter("mail_server");
	String tel1				= request.getParameter("tel1");
	String tel2				= request.getParameter("tel2");
	String tel3				= request.getParameter("tel3");
	String social1			= request.getParameter("social1");
	String social2			= request.getParameter("social2");
	String gender			= request.getParameter("gender");
	//직접 입력한 이메일 서버가 있다면, 그걸 우선해주고, 없다면 select박스 값을넣는다.
	String email=null;
	//email = mail_id
	String s =null;
	if(myserver.length()>0){//직접 입력을 한 경우
		s=myserver; 
	}else{
		s=mail_server;
	}
	email = mail_id+"@"+s;

	out.print("userid "+user_id);
	out.print("<br>");
	out.print("pass "+pass);
	out.print("<br>");
	out.print("username "+user_name);
	out.print("<br>");
	out.print("maili "+mail_id);
	out.print("<br>");
	out.print("server "+mail_server);
	out.print("<br>");
	out.print("social "+social1);
	out.print("<br>");
	out.print("social2 "+social2);
	out.print("<br>");
	out.print("gender "+gender);
	out.print("<br>");

	/*오라클 테이블에 레코드 삽입 
		1)해당 DBMS제품에 맞는 드라이버 로드 
		2)접속
		3)쿼리문
		4)DB관련 연결 닫기
	*/
	Class.forName("oracle.jdbc.driver.OracleDriver");

	Connection con = DriverManager.getConnection(url,user,password);
	if(con==null){out.print("오라클 접속 실패<br>");}else{out.print("성공");}

	String sql ="INSERT INTO member(member_id,user_id,pass,user_name,email,tel1,tel2,tel3,social1,social2,gender)";
	sql+=" VALUES(seq_member.nextval,?,?,?,?,?,?,?,?,?,?)";

	//PreparedStatement 는 인터페이스이므로, new로 직접 생성할 수 없는데 Connection객체로부터 인스턴스를 얻어올 수 있다. 즉 접속이 성공되면 얻어올 수 있다.
	PreparedStatement pstmt=con.prepareStatement(sql);//인터페이스임
	//바인드 변수값 할당
	pstmt.setString(1,user_id);
	pstmt.setString(2,pass);
	pstmt.setString(3,user_name);
	pstmt.setString(4,email);
	pstmt.setString(5,tel1);
	pstmt.setString(6,tel2);
	pstmt.setString(7,tel3);
	pstmt.setString(8,social1);
	pstmt.setString(9,social2);
	pstmt.setString(10,gender);

	int result = pstmt.executeUpdate();//DML 수행 메소드 
	if(result==0){out.print("입력실패");
	}else out.print("입력성공");

	if(pstmt!=null)pstmt.close();
	if(con!=null)con.close();
%>