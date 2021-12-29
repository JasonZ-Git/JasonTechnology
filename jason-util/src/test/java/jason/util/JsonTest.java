package jason.util;

import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.google.gson.Gson;

public class JsonTest {

  @Test
  public void gsonTest() {
    Gson gson = new Gson();
    String jsonStr = "{ \"author\": \"Jason\", \"title\" : \"architecture\", \"obj\" : {\"objint\" : {}} }";
    Object obj = gson.fromJson(jsonStr, Object.class);

    Map m = gson.fromJson(jsonStr, Map.class);
    Assertions.assertEquals(3, m.size());
    Assertions.assertEquals("Jason", m.get("author"));
    Assertions.assertEquals("architecture", m.get("title"));
    // Assert.assertEquals("{objint={}}", m.get("obj"));

    Book book = gson.fromJson(jsonStr, Book.class);
    Assertions.assertEquals("Jason", book.getAuthor());
    Assertions.assertEquals("architecture", book.getTitle());
  }
}


class Book {
  private String author;
  private String title;
  private Map obj;

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Map getObj() {
    return obj;
  }

  public void setObj(Map obj) {
    this.obj = obj;
  }

}
