package org.jason.java5;

public class ClassEnumTest {
	public ClassEnumTest ONE;
	public ClassEnumTest TWO;
	public ClassEnumTest THREE = new ClassEnumTest(3);
	
	public ClassEnumTest() {}
	public ClassEnumTest(int age) {}
}

enum RealEnum{
	ONE, TWO, THREE(3);
	RealEnum() {}
	RealEnum(int age) {}
}
