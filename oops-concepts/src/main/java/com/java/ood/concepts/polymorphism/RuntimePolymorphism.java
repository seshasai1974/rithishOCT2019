package com.java.ood.concepts.polymorphism;



public class RuntimePolymorphism {
	
	public void bowlingmethod() {
		System.out.println(" Runtime method 1");
	}
	
	
	public static void main(String[] args) {
		
		RuntimePolymorphism1 runimepolym = new RuntimePolymorphism1();
		runimepolym.bowlingmethod();
	}

}
