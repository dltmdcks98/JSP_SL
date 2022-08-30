package com.academy.web0829.board.repository;

import java.util.List;

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
		result = sqlSession.insert("Board.insert",board);
		sqlSession.commit();
		configManager.closeSqlSession(sqlSession);
		return result;
	}
	
	//목록 가져오기 
	public List selectAll() {
		SqlSession sqlSession = configManager.getSqlSession();
		List list =null;
		list = sqlSession.selectList("Board.selectAll");
		configManager.closeSqlSession(sqlSession);
		return list;
	}
	
	//한건 가져오기
	public Board select(int board_id) {
		SqlSession sqlSession = configManager.getSqlSession();
		Board board = null;
		board = sqlSession.selectOne("Board.select", board_id);
		configManager.closeSqlSession(sqlSession);
		
		return board;
	}
}
