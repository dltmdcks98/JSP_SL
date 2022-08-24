package com.aca.web0812.pattern;

public class Dog {
	private static Dog instance;
	private String name= "영구";
	
	//생성자 호출을 막았으므로, 생성자 호출 외의 다른 방법으로 인스턴스를 얻어가게 해야함
	private Dog() {
		
	}
	public static Dog getInstance() {
		if(instance==null) {
			instance = new Dog();
		}
		return instance;
	}
}
