package com.aca.web0812.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aca.web0812.domain.HotSpot;

/*오직 Hot Spot 테이블에 대한 CRUD를 담당하는 DAO객체*/
public class HotSpotDAO {
	String url="jdbc:mysql://localhost:3306/javastudy?useUnicode=true&characterEncoding=utf8";
	String user="root";
	String pass="1234";
	public int insert(HotSpot hotSpot) {
		Connection con = null;
		PreparedStatement pstmt=null;
		int result =0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pass);
			String sql = "insert into hotspot(lati, longi, icon, content) values(?,?,?,?)";
			pstmt= con.prepareStatement(sql);
			pstmt.setFloat(1, hotSpot.getLati());
			pstmt.setFloat(2, hotSpot.getLongi());
			pstmt.setString(3, hotSpot.getIcon());
			pstmt.setString(4, hotSpot.getContent());
			result=pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	//목록 가져오기
	public List selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pass);
			String sql = "SELECT * FROM hotspot ORDER BY hotspot ASC";
			pstmt= con.prepareStatement(sql);
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				//레코드 한건을 대체하기 위한 DTO 하나의 인스턴스 생성
				HotSpot dto= new HotSpot();
				dto.setHotspot_id(rs.getInt("hotspot"));
				dto.setLati(rs.getFloat("lati"));
				dto.setLongi(rs.getFloat("longi"));
				dto.setIcon(rs.getString("icon"));
				dto.setContent(rs.getString("content"));
				
				list.add(dto);//2차원 구조 생성
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}

}
