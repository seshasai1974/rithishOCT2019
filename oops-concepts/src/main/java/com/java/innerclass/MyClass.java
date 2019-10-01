package com.java.innerclass;
class Outer_Demo {
   int num = 10;
   
   public void print() {
       System.out.println("This is an outer class : " + num);
    }
   
   // inner class
   private class Inner_Demo {
	   int num1 = 5;
      public void print() {
         System.out.println("This is an inner class : " + num1);
         System.out.println("This is an inner class : " + num);
        // System.out.println("This is an inner class : " + Outer_Demo.);
      }
   }
   
   // Accessing he inner class from the method within
   void display_Inner() {
      Inner_Demo inner = new Inner_Demo();
      inner.print();
   }
}
   
public class MyClass {

   public static void main(String args[]) {
      // Instantiating the outer class 
      Outer_Demo outer = new Outer_Demo();
      
      outer.print();
      // Accessing the display_Inner() method.
      outer.display_Inner();
   }
}