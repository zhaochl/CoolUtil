package com.zcl.varargs;

public class VarArgumentTest {

	public static void test(String s){
		System.out.println("call ss");
	}
	public static void test(String s,String... strs){
	    System.out.print("var argument ss");
	    for(String a:strs)
	        System.out.print(a+" ");
	    System.out.println("!");
	}
	public static void main(String[] args){
	    test("hello");
	    test("hello","word");
	    test("this","is","the","hello","word");
	}
}
