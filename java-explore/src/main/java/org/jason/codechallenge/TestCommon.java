package org.jason.codechallenge;

public class TestCommon {

  {
    
    Parent p = new Inherited();
    
    // Inherited t1 = p; - compile error
    
    Inherited t2 = (Inherited)p;
  }
}



class Inherited implements Parent {};

interface Parent{};