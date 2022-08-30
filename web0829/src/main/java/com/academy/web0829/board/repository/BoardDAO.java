package com.academy.web0829.board.repository;

import org.apache.ibatis.session.SqlSession;

import com.academy.web0829.domain.Board;
import com.academy.web0829.mybatis.ConfigManager;

public class BoardDAO {
	ConfigManager configManager = ConfigManager.getInstance();
	
	public int insert(Board board) {
		SqlSession sqlSession = configManager.getSqlSession();//mybatis 쿼리 수행 객체
		int result = 0;
		//여기서 sql문 작성하지 않고 XML에 작성된 쿼리문을 호출 
//		sqlSession.insert("쿼리문을 넣어놓은  xml파일의 id",DTO);
		result = sqlSession.insert("babo.insert",board);
		sqlSession.commit();
		configManager.closeSqlSession(sqlSession);
		return result;
	}
}
