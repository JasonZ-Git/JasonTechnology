package org.jason.java7;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class JavaNioStudy {
  @Test
  public void nioExplore(){
    System.out.println(Files.exists(Paths.get("hello")));
  }
}
