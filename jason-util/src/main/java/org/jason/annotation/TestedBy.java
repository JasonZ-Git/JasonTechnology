package org.jason.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This is used to linke the real class and test class together. 
 * 
 * As Test class are not visiable in compile path, so we have to use a string instead of class
 * 
 * @author Jason Zhang
 */

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface TestedBy {
  String className() default "Test Class Name";
}
