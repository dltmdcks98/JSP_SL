package com.academy.web0829.util;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import lombok.Data;

@Data
public class Pager {
	private int totalRecord;//총레코드 수 
	private int pageSize =0 ;;//페이지당 보여질 레코드 수 
	private int totalPage;
	private int blockSize=10;//블럭당 보여질 페이지 수 
	private int currentPage=1;//처음 들어왔을때
	private int firstPage;//블럭당 시작 페이지
	private int lastPage;//블럭당 마지막 페이지
	private int curPos; //페이지당 시작 index(커서의 위치와 같다.)
	private int num;//페이지당 게시물 시작 번호
		
	//공식을 대입하기 위함 
	public void init(List list, HttpServletRequest request) {
		totalRecord = list.size();
		totalPage = (int)Math.ceil((float)totalRecord/pageSize);
		if(request.getParameter("currentPage")!=null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		firstPage = (currentPage-1)% blockSize;
		lastPage = firstPage+(blockSize-1);
		curPos = (currentPage-1)*pageSize;
		num = totalRecord-curPos;
	}
}
