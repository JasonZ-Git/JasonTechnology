package org.jason.test;

import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTester {

  @Before
  public void setUp() throws Exception {}

  @After
  public void tearDown() throws Exception {}

  @Test
  public void test() throws JsonParseException, JsonMappingException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    String jsonString = "{\"name\":\"Jason\",\"age\":30}";

    // map json to student
    Student student = mapper.readValue(jsonString, Student.class);
    Assert.assertEquals(student.getName(), "Jason");
    // mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
    String tempJsonString = mapper.writeValueAsString(student);
    Assert.assertEquals(tempJsonString, jsonString);
  }

}


class Student {
  private String name;
  private int age;

  public Student() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String toString() {
    return "Student [ name: " + name + ", age: " + age + " ]";
  }
}
