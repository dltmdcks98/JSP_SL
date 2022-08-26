package com.aca.web0812.reboard.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.aca.web0812.pool.ConnectionManager;
import com.aca.web0812.pool.PoolManager;
import com.aca.web0812.reboard.domain.ReBoard;

public class ReBoardDAO {
	ConnectionManager manager = PoolManager.getInstance();
	public int insert(ReBoard reBoard) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		con=manager.getConnection();
		String sql = "INSERT INTO reboard(reboard_id, title, writer, content, team)";
		sql += "values(seq_reboard.nextval, ?,?,?, seq_reboard.nextval)";//시퀀스의 증가는 세션이 끝날때 증가되는거 
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reBoard.getTitle());
			pstmt.setString(2, reBoard.getWriter());
			pstmt.setString(3, reBoard.getContent());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt);
		}
		return result;
	}
}
