package com.aca.web0812.pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//웹에서의 커넥션 풀로부터 커넥션을 얻기위한 전담 객체
public class PoolManager extends ConnectionManager{
	InitialContext context;//JNDI 검색 객체
	DataSource ds;
	
	
	public PoolManager() {
		try {
			context=new InitialContext();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Connection getConnection() {
		Connection con=null;
		try {
			ds = (DataSource) context.lookup("java:comp/env/jdbc/myoracle");//검색시작
			con = ds.getConnection();//새로운 접속이 아니라 기존 풀에 모여있는 Connection을 빌려옴
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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