package com.aca.web0812.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.aca.web0812.domain.Comments;
import com.aca.web0812.pool.ConnectionManager;
import com.aca.web0812.pool.PoolManager;

/*
 * DAO는 테이블마다 1:1 대응하게 생성해야함 따라서 오라클에 table이 100개 라면 DAO도 100개
 * 제작시에는 시간이 좀 걸리지만, 추후 유지보수 할때는 시간이 단축된다. 
 * */
public class CommentsDAO {
	ConnectionManager manager=PoolManager.getInstance();//다형성을 염두(코드가 유연해짐)
	//메모리에 클래스가 올라올때 생성

	public int insert(Comments comments) {
		 Connection con =null;
		 PreparedStatement pstmt = null;
		 int result =0;
		 
		 con=manager.getConnection();
		 String sql="INSERT INTO comments(comments_id, detail, author) VALUES(seq_comments,nextval, ?,?)";
		 try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, comments.getDetail());
			pstmt.setString(2, comments.getAuthor());
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			manager.freeConnection(con,pstmt);
		}
		 
		 return result;
	 }
}