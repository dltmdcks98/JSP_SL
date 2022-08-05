<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import ="com.oreilly.servlet.MultipartRequest" %>
<%@ page import ="java.sql.*" %>
<%@ page import ="java.io.File" %>
<%!															//테이블명    데이터 입력 한글 
	String url ="jdbc:mysql://localhost:3306/javastudy?useUnicode=true&characterEncoding=utf8";
	String user="root";
	String password="1234";
	
	//클래스의 멤버 변수는 컴파일러에 의해 자동 초기화 된다 숫자영역 :0 문자 : null
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
//확장자를 추출하는 메서드
	public String getExt(String path)
	{
		return path.substring(path.lastIndexOf(".")+1,path.length());

	}
%>
<%
//클라이언트가 보낸 파일을 서버에 업로드 처리하려면, 많은 로직을 수반되므로 누군가 만들어놓은 라이브러리를 이용한다,cos.jar
//cos.jar의 MultipartRequest클래스가 업로드를 처리하는 클래스
//	String saveDir="D:/LSC/JSP_SL/jsp0805/data";//저장경로 \ 가 아니라 /로 해야함 => 절대경로 방식
/*위와 같이 이미지가 업로드될 경로를 해당 os플랫폼에 맞게 고정시켜 버리면 다른 플랫폼기만에서는 
어플리케이션을 수정해야하므로 위와 같은 하드코딩은 안하는 것이 좋다. 해결책은 jsp내장 객체중 현재 웹 어플리케이션의 전반적인 정보를 다루는 객체인
application 객체를 이용*/
	String saveDir=application.getRealPath("/data");
	out.print("data 디렉토리의 실제 위치"+saveDir+"<br>");
	int maxSize=1024*1024*3;//3M
	String encoding="utf-8";
	MultipartRequest multi = new MultipartRequest(request,saveDir,maxSize,encoding);

	//클라이언트의 브라우저에서 전송한 파라미터(전송되어온 변수)들을 받자
/*	request.setCharacterEncoding("utf-8");
	String title = request.getParameter("title");
	String spot = request.getParameter("spot");
	String detail = request.getParameter("detail");
	//myfile은 String이 아님getParameter는 글자만 받는다.*/
//	out.print("title");하면 null로 나오는데 form을 multipart로 처리 되서 그럼 이후에는 아래와 같이 입력해야함

	String title = multi.getParameter("title");
	String spot = multi.getParameter("spot");
	String detail = multi.getParameter("detail");	
	//업로드한 파일의 원래 이미지명 얻기
	File file = multi.getFile("myfile");//javaSE의 file을 반환 
	String filename = file.getName();
	out.print("업로드한 파일"+filename+"<br>");



	//파일의 이름을 아직 바꾸지 않았으므로 insert를 먼저 진행한 후 그 쿼리로부터 생성된 레코드의 primary key를 이용하여 파일명을 사용
	/*1)드라이버 로드 2) 접속 3)쿼리 수행 4)자원해제*/ 
	//1)드라이버 로드
	Class.forName("com.mysql.jdbc.Driver");
	//2)접속
	con = DriverManager.getConnection(url,user,password);
	if(con==null){
		out.print("연결 실패");
	}else out.print("성공");
	

	//업로드 = insert+update 모두 성공해야 성공으로 간주하는 트랜잭션 상황 
	//즉 세부 업무가 2개짜리 트랜잭션
	con.setAutoCommit(false);//insert문을 수행한다고 하더라도 트랜잭션이 확정되지 않는다.

	try{
		String sql = "INSERT INTO gallery(title,spot,detail) VALUES(?,?,?)";
		pstmt= con.prepareStatement(sql);
		pstmt.setString(1,title);
		pstmt.setString(2,spot);
		pstmt.setString(3,detail);

		int result =pstmt.executeUpdate();//DML의 경우
		if(result ==0){
			out.print("등록실패");
		}else{
			out.print("등록성공");
				//파일명 바꾸기 연습
				//	file.renameTo(바꾸게될 파일)
				/*현재 이 Connection에서 일으킨 가장 마지막 Primary key를 얻어오는 방법 MAX는 다른 사용자 값도 가져옴*/
				sql="SELECT last_insert_id() AS gallery_id";
				pstmt=con.prepareStatement(sql);
				rs = pstmt.executeQuery();//SELECT 문 실행시

				int gallery_id=0;
				if(rs.next()){
					gallery_id = rs.getInt("gallery_id");
				}
				String lastName = gallery_id+"."+getExt(filename);
				boolean result2=file.renameTo(new File(saveDir+"/"+lastName));
				if(result2){
					//filename 컬럼 업데이트
					sql ="UPDATE gallery SET filename=? WHERE gallery_id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1,lastName);
					pstmt.setInt(2,gallery_id);
					pstmt.executeUpdate();

				}
			}
		con.commit();//트랜잭션 성공
	}catch(SQLException e){
		con.rollback();//트랜잭션 실패
	}finally{
		con.setAutoCommit(true);//원상복귀
	}
	
	if(rs!=null)rs.close();
	if(pstmt!=null)pstmt.close();
	if(con!=null)con.close();
	response.sendRedirect("/list.jsp");

%>