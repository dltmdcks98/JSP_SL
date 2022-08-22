package com.aca.web0812.map;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aca.web0812.domain.HotSpot;
import com.aca.web0812.model.HotSpotDAO;

//데이터 목록을 구하는 서블릿
public class ListServlet extends HttpServlet{
	HotSpotDAO hotspotDAO;//init()이랑 동일

	public void init() throws ServletException {
		 hotspotDAO = new HotSpotDAO();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//DB와의 CRUD는 별도의 전담객체(DAO)로 선언
		
		response.setContentType("text/html;charset=utf-8");//jsp에서의 page 지시영역
		
		PrintWriter out = response.getWriter();
		
		List<HotSpot> list = hotspotDAO.selectAll();
		//list를 json으로
		out.print("[");
		//아래처럼 하지는 않고 GSON이라는 라이브러리를 사용
		//SPRING 사용시 자동으로 JSON 배열을 생성 
		for(int i=0; i<list.size();i++) {
			HotSpot dto = list.get(i);
			
		out.print("{");
		out.print("\"hotspot:\":"+dto.getHotspot_id()+",");
		out.print("\"lati:\":"+dto.getLati()+",");
		out.print("\"longi:\":"+dto.getLongi()+",");
		out.print("\"icon:\":"+dto.getIcon()+"\",");
		out.print("\"icon:\":"+dto.getContent()+"\"");
		if(i<list.size()-1) {
			out.print("},");
		}else {
			out.print("}");
		}

		}
		out.print("]");
		
	}
}
