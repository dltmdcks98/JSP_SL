package com.academy.web0829.emp.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.academy.web0829.mybatis.ConfigManager;

public class EmpDAO {
	ConfigManager manager = ConfigManager.getInstance();
	
	public List selectAll() {
		SqlSession sqlSession = manager.getSqlSession();
		List list = null;
		list = sqlSession.selectList("Emp.selectAll");
		manager.closeSqlSession(sqlSession);
		
		return list;
	}
}
