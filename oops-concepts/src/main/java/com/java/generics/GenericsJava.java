package com.java.generics;
public class GenericsJava<T> {
   private T t;

   public void add(T t) {
      this.t = t;
   }

   public T get() {
      return t;
   }

   public static void main(String[] args) {
      GenericsJava<Integer> integerGenerics = new GenericsJava<Integer>();
      
      GenericsJava<String> stringGenerics = new GenericsJava<String>();
      
      GenericsJava<Double> doubleGenerics = new GenericsJava<Double>();
    
      integerGenerics.add(new Integer(10));
      stringGenerics.add(new String("String Generics"));
      doubleGenerics.add(new Double(3434343));

      System.out.printf("Integer Value :%d\n\n", integerGenerics.get());
      System.out.printf("String Value :%s\n", stringGenerics.get());
   }
}