package com.java.datatype;

import java.math.BigDecimal;

/*
 * seshasai
 */

class DataTypes 
{ 
    public static void main(String args[])  
    { 
        // declaring character 
    	//0 to 65,536 (unsigned)
         char a = 'a'; 
           
        // Integer data type is generally  
        // used for numeric values 
         //-2,147,483,648 to 2,147,483, 647
        int i=89; 
          
        // use byte and short if memory is a constraint 
        //-128 to 127
        byte b = -128; 
          
        // this will give error as number is  
        // larger than byte range 
        // -32,768 to 32,767 
        short s = 56;
        
        //long -9,223,372,036,854,775,808 to  9,223,372,036,854,775,807
        long l = 45274527;
          
        // this will give error as number is  
        // larger than short range 
        // short s1 = 87878787878; 
          
          
        // by default fraction value is double in java 
        //±1.79769313486231570E+308
        double d = 4.355453532; 
          
        // for float use 'f' as suffix 
        //±3.40282347E+38F
        float f = 4.7333434f; 
        
        Integer integer = new Integer(1222);
        
        //BigDecimal will be used in financial transaction for a conforming the results every tie they are same

        
        System.out.println("char: " + a);  
        System.out.println("int: " + i);  
        System.out.println("byte: " + b);  
        System.out.println("short: " + s);  
        System.out.println("long: " + l);  
        System.out.println("float: " + f);  
        System.out.println("double: " + d);  
        
    }     
}