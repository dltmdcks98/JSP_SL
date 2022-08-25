package com.aca.web0812.model;

public class StringTest {
	
	/*
	 * Java언어의 String 특징
	 * 1)immutable : 불변 
	 */
	public static void main(String[] args) {
		//암시적 생성법 new로 하지 않고 객체로 생성->new로 생성하지 않으면 상수풀에 생성이 되는데
		//이 상수풀은 heap 영역에 저장되고 String의 내용이 같으면 따로 저장하지 않음 단, 주소를 참조
		//아래의 코드에서는 b 는 a와 주소가 같음 
		String a = "tiger";
		String b = "tiger";

		//아래와 같이 b를 변경하게되면 a에서 저장된 상수풀의 내용을 변경하지 않고, 새로 상수 풀에 생성된다.
		b="tiger king";
		
		String x = new String("korea");
		String y = new String("korea");
		
		System.out.println(a==b);
		System.out.println(x==y);
	}

}
