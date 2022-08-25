package com.aca.web0812.news.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aca.web0812.domain.News;
import com.aca.web0812.pool.ConnectionManager;
import com.aca.web0812.pool.PoolManager;


/*
 * 기존 코드방식에서랑은 다르게, Connection 객체는 커넥션 풀을 이용한다
 * 우리가 사용하는 커넥션풀은 Tomcat 서버가 제공하는 풀을 이용하되, JNDI로 자원에 접근할 예정
 */
public class NewsDAO {
	ConnectionManager manager;//웹이건 응용이건 둘다 포함 할수 있는 객체
	
	public NewsDAO(){
		//manager = new PoolManager();//웹용으로 생성 
		manager = PoolManager.getInstance();//위의 코드를 싱글톤으로 받아옴
		
	}
	//Create
	public int insert(News news) {
		Connection con=null;
		int result=0;
		PreparedStatement pstmt=null;
		try {
			con=manager.getConnection();//다형성: 자료형은 ConnectionManager이지만, 
			//호출되는 메서드 동작은 각각 틀리게 동작할 수 있다. ConnectionManger가 어떤 때는 PoolManager로 동작 DBManager로 동작할 수 있다.
			String sql="insert into news(news_id, title, writer, content) values(seq_news.nextval,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, news.getTitle());
			pstmt.setString(2, news.getWriter());
			pstmt.setString(3, news.getContent());
			
			result= pstmt.executeUpdate();//DML 수행
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt);
		}
		return result;
	}
	//Read
	public List selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		List<News> list = new ArrayList<News>();
		
		con=manager.getConnection();
		
		//일단 메모리상에 생성된 스트링은 절대 수정이 불가능한 불변(immutable)의 특징을 가지므로
		//아래와 같이 String을 대상으로 누적시키거나 반복문을 돌릴경우 선능에 문제가 발생한다.
		//해결책=> 수정 가능한 버퍼처리된 String객체(StringBuilder,StringBuffer)를 사용
		/*String sql = "";
		sql+="SELECT news_id, title ,writer ,regdate ,hit, COUNT(nid) as cnt";  
		sql+=" FROM"; 
		sql+="(";
		sql+="SELECT n.news_id AS news_id,title ,writer ,regdate ,hit, c.news_id  AS nid"; 
		sql+=	" FROM news n LEFT OUTER JOIN  comments c"; 
		sql+=	" ON n.news_id =c.news_id";
		sql+=") GROUP BY news_id, title ,writer, regdate ,hit";
		*/
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT news_id, title ,writer ,regdate ,hit, COUNT(nid) as cnt");
		sb.append(" FROM");
		sb.append("(");
		sb.append("SELECT n.news_id AS news_id,title ,writer ,regdate ,hit, c.news_id  AS nid");
		sb.append(" FROM news n LEFT OUTER JOIN  comments c");
		sb.append(" ON n.news_id =c.news_id");
		sb.append(") GROUP BY news_id, title ,writer, regdate ,hit");
		
		System.out.println(sb.toString());
		
		try {
			pstmt=con.prepareStatement(sb.toString());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				News news = new News();
				news.setNews_id(rs.getInt("news_id"));
				news.setTitle(rs.getString("title"));
				news.setWriter(rs.getString("writer"));
				news.setRegdate(rs.getString("regdate"));
				news.setHit(rs.getInt("hit"));
				news.setCnt(rs.getInt("cnt"));
				
				list.add(news);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			manager.freeConnection(con,pstmt,rs);
		}
		return list;
	}
	public News select(int news_id) {
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		News news=null;//return 하려고
		
		con = manager.getConnection();
		String sql="select * from news where news_id=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, news_id);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {//레코드가 있다면
				news = new News();
				news.setNews_id(rs.getInt("news_id"));
				news.setTitle(rs.getString("title"));
				news.setWriter(rs.getString("writer"));
				news.setContent(rs.getString("content"));
				news.setRegdate(rs.getString("regdate"));
				news.setHit(rs.getInt("hit"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt, rs);
		}
		return news;
	}
	//update
	public void update() {
		String sql="update news set title=?, writer?, content=? where news_id=?";
	}
	//delete
	public int delete(int news_id) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result = 0;
		
		con = manager.getConnection();
		
		String sql="select * from comments where news_id=?";//자식이 있는지 조회
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, news_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {//자식이 있는 경우
				sql="update news set title='원본이 삭제된 게시물입니다', writer='', content='냉무' where news_id=?";
				pstmt= con.prepareStatement(sql);
				pstmt.setInt(1, news_id);
				result = pstmt.executeUpdate();//수정 실행
				
			}else {
				sql="delete from news where news_id=?";
				pstmt= con.prepareStatement(sql);
				pstmt.setInt(1, news_id);
				result = pstmt.executeUpdate();
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt, rs);
		}
		return result;
	}
}