package web0809.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//수정요청을 처리하는 서블릿
public class EditServlet extends HttpServlet {
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "java";
	String password = "1234";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String board_id = request.getParameter("board_id");//방금본 게시물pk
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out= response.getWriter();

		String sql = "UPDATE board SET title=?, writer=?, content=?";
		sql += "WHERE board_id="+board_id ;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			out.print("드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection con = null;
		PreparedStatement pstmt =null; 
		try {
			con = DriverManager.getConnection(url, user, password);
			if(con==null) {
				out.print("접속실패");
			}else {
				out.print("접속성공");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, content);
			
			int result =pstmt.executeUpdate();
			if(result<1) {
				out.print("수정실패<br>");
			}else {
				out.print("수정성공 <br>");
				//톰켓이 응답정보를 클라이언트에게 보낼때 해당 클라이언트의 브라우저로 하여긂 재접속할 주소를 기입 바로 이동하는게 아님 
				response.sendRedirect("/board/list.jsp");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
