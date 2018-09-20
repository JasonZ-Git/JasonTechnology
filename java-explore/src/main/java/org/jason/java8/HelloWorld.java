package org.jason.java8;

public class HelloWorld {
	public static void main(String[] args) {
		System.err.println(new Throwable().getStackTrace()[0].getLineNumber());
		System.out.println("main method");
		System.err.println(new Throwable().getStackTrace()[0].getLineNumber());
		
		anotherMethod();
	}
	
	public static void anotherMethod() {
		System.out.println("another method");
		System.err.println(new Throwable().getStackTrace()[0].getLineNumber());
	}
	
}
