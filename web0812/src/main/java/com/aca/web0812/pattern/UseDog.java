package com.aca.web0812.pattern;

public class UseDog {
	public static void main(String[] args) {
		Dog d1 = Dog.getInstance();
		Dog d2 = Dog.getInstance();
		
		
		System.out.println(d1);
		System.out.println(d2);
	}
}
