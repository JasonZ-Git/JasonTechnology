package org.jason.java8.functioninterface;

@FunctionalInterface
public interface JasonPredicate<T>{
 public boolean check(T t);
 
 default public void printToConsole(T t) {
   System.out.println(t);
 }
}
