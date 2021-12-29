package jason.util;

import java.io.IOException;
import java.util.List;
import org.jason.util.JasonFileUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JasonFileUtilTester {

  @Test
  public void testFileUtil() {
    String fileName = "src/test/resources/testfile.txt";
    List<String> lines = null;
    try {
      lines = JasonFileUtil.readFile(fileName);
    } catch (IOException e) {
      e.printStackTrace();
    }

    Assertions.assertEquals(2, lines.size());
    Assertions.assertEquals("Hello", lines.get(0));
    Assertions.assertEquals("World", lines.get(1));
  }
}
