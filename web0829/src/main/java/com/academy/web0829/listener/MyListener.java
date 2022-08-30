package com.academy.web0829.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener{
	String name = "superman";
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("서버 가동 감지");
		//application 내장객체에 name 을 담아놓으면, 프로그램이 끝날때 까지 name을 사용할 수 있다.
		ServletContext application = sce.getServletContext();
		application.setAttribute("nick", name);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("서버 종료 감지");
	}
}
