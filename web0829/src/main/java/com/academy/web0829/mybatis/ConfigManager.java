package com.academy.web0829.mybatis;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
 * mybatis의 설정 정보는 요청이 있을 때 마다, xml을 읽어들일 필요 없고
 * 프로그램이 실행되면 한번만
 * */
public class ConfigManager {
	private static ConfigManager instance;//싱글톤
	
	SqlSessionFactory sqlSessionFactory;

	private ConfigManager() {
		try {
			String resource = "com/academy/web0829/mybatis/config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			// mybatis를 이용하면 기존에 jdbc에서 sql문 수행시 사용했던 PreparedStatement 를 사용하는게 아니라
			// 대신 SqlSessio이라는 객체를 이용한다. 아래의 SqlSessionFactory는 SqlSession을 관리 및 반환해주는 객체
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//sqlSessionFactory로부터 SqlSession을 얻어갈 수 있도록 메소드를 정의
	public SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();

	}

	public void closeSqlSession(SqlSession sqlSession) {
		sqlSession.close();
	}

	public static ConfigManager getInstance() {
		if(instance==null) {
			instance = new ConfigManager();
		}
		return instance;
	}
}
