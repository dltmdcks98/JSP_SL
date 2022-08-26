package com.aca.web0812.reboard.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	public List selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		List<ReBoard> list = new ArrayList<ReBoard>();
		
		con = manager.getConnection();
		String sql ="SELECT * FROM reboard ORDER BY team DESC, step ASC ";
		
		try {
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ReBoard reBoard = new ReBoard();
				reBoard.setReboard_id(rs.getInt("reboard_id"));
				reBoard.setTitle(rs.getString("title"));
				reBoard.setWriter(rs.getString("writer"));
				reBoard.setContent(rs.getString("content"));
				reBoard.setRegdate(rs.getString("regdate"));
				reBoard.setHit(rs.getInt("hit"));
				reBoard.setTeam(rs.getInt("team"));
				reBoard.setStep(rs.getInt("step"));
				reBoard.setDepth(rs.getInt("depth"));
				
				list.add(reBoard);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt, rs);
		}
		
		return list;
	}
	
	//레코드 한건 가져오기
	public ReBoard select(int reboard_id) {
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs=null;
		ReBoard reBoard=null;
		
		con = manager.getConnection();
		String sql = "SELECT * FROM reboard WHERE reboard_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reboard_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reBoard = new ReBoard();
				
				reBoard.setReboard_id(rs.getInt("reboard_id"));
				reBoard.setTitle(rs.getString("title"));
				reBoard.setWriter(rs.getString("writer"));
				reBoard.setContent(rs.getString("content"));
				reBoard.setRegdate(rs.getString("regdate"));
				reBoard.setHit(rs.getInt("hit"));
				reBoard.setTeam(rs.getInt("team"));
				reBoard.setStep(rs.getInt("step"));
				reBoard.setDepth(rs.getInt("depth"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt,rs);
		}
		
		
		return reBoard;
	}
	
	//답변 등록
	public int reply(ReBoard reBoard) {
		Connection con =null;
		PreparedStatement pstmt = null;
		int result =0;
		
		con = manager.getConnection();
		
		try {
			//답변이 들어갈 자리 확보 (내가 본 글보다 step이 큰 글들의 step을 1씩 증가시키되, 같은 team내에서면
//		String sql = "UPDATE reboard SET step=step+1 WHERE team=내본글team,step>내본글step";
			String sql = "UPDATE reboard SET step=step+1 WHERE team=? AND step > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reBoard.getTeam());
			pstmt.setInt(2, reBoard.getStep());
			result = pstmt.executeUpdate();
			
			
				//답변 INSERT
				sql = "INSERT INTO reboard(reboard_id,title, writer, content, team, step, depth)";
//				sql+= " VALUES(seq_reboard.nextval,?,?,?, 내본글team,내본글 step+1,내본글depth+1"
				sql+= " VALUES(seq_reboard.nextval,?,?,?,?,?,?)";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, reBoard.getTitle());
				pstmt.setString(2, reBoard.getWriter());
				pstmt.setString(3, reBoard.getContent());
				pstmt.setInt(4, reBoard.getTeam());//내본글 team
				pstmt.setInt(5, reBoard.getStep()+1);//내본글 step+1 : 내본글 밑에 와야함
				pstmt.setInt(6, reBoard.getDepth()+1);//내본글 depth+1 : 내가 본 글의 답변이므로 
				
				result =pstmt.executeUpdate();
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt);
		}
		return result;
	}
}
