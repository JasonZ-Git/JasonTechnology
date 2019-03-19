This is my thinking of improving java

## double & float issue
First, float should be removed, and only keep double.
Second, float & double cannot be precisely represented decimal number, while we usually use decimal to do calculation.
We should introduce a Number (currently in Java, Number is a abstract class for Long, Short, and others)
