package com.aca.web0812.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aca.web0812.domain.Comments;
import com.aca.web0812.domain.News;
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
		 String sql="INSERT INTO comments(comments_id, detail, author, news_id) VALUES(seq_comments.nextval, ?,?,?)";
		 try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, comments.getDetail());
			pstmt.setString(2, comments.getAuthor());
			pstmt.setInt(3, comments.getNews().getNews_id());//News객체로 등록했기 때문에 2번 치고 들어가야함
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			manager.freeConnection(con,pstmt);
		}
		 
		 return result;
	 }
	
	//모든 레코드 가져오기 (해당 뉴스 기사에 관련된 )
	public List selectAll(int news_id) {
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		List list = new ArrayList();
		
		con = manager.getConnection();
		
		String sql = "SELECT * FROM comments WHERE news_id=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, news_id);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Comments comments = new Comments();
				comments.setComments_id(rs.getInt("comments_id"));
				
				News news = new News();
				news.setNews_id(news_id);
				comments.setNews(news);
				
				comments.setDetail(rs.getString("detail"));
				comments.setAuthor(rs.getString("author"));
				comments.setWritedate(rs.getString("writedate"));
				
				list.add(comments);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			manager.freeConnection(con,pstmt,rs);
		}
		return list;
	}
	
}
