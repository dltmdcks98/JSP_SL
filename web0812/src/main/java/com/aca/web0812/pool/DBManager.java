package com.aca.web0812.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.sql.DataSource;

//독립실행형에서 커넥션을 관리하기 위한 객체
public class DBManager extends ConnectionManager{
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="java";
	String password="1234";
	
	@Override
	public Connection getConnection() {
		Connection con=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,user,password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	@Override
	public void freeConnection(Connection con) {
		// TODO Auto-generated method stub
		if(con!=null){
			try {
				con.close();//자원을 해제하는 것이 아니라 반납하는것 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void freeConnection(Connection con, PreparedStatement pstmt) {
		// TODO Auto-generated method stub
		if(con!=null){
			try {
				con.close();//자원을 해제하는 것이 아니라 반납하는것 
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
	}

	@Override
	public void freeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
		// TODO Auto-generated method stub
		if(con!=null){
			try {
				con.close();//자원을 해제하는 것이 아니라 반납하는것 
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
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}