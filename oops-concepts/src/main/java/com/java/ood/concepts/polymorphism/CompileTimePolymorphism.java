package com.java.ood.concepts.polymorphism;

public class CompileTimePolymorphism {
	
	static int add(int a,int b) {
		System.out.println(" inside int");
		return a+b;
	}
	
	static double add(double a,double b) {
		System.out.println(" inside double");
		return a+b;
	}
	
	public static void main(String[] args) {
		
		System.out.println(" " + CompileTimePolymorphism.add(2, 3) );
		System.out.println(" " + CompileTimePolymorphism.add(2.2, 3.3) );
	}

}
