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

public class DeleteServlet extends HttpServlet {
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "java";
	String password = "1234";

	// 클러아이언트가 board_id 파라미터를 get방식으로 보낼 예정
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// DELETE board WHERE board_id=?
		String board_id = request.getParameter("board_id");

		PrintWriter out = response.getWriter();
		out.print("DELETE board WHERE board_id=" + board_id);

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, password);
			String sql = "DELETE board WHERE board_id=" + board_id;
			pstmt = con.prepareStatement(sql);
			int result = pstmt.executeUpdate();
			if (result < 1) {
				out.print("삭제실패");
			} else {
				response.sendRedirect("/board/list.jsp");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
}
