package jason.util;

import java.io.IOException;

import org.jason.util.JasonTimeZoneUtil;
import org.jason.util.finalclass.GoogleTimeZone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


public class JasonTimeZoneUtilTest {

  @Test
  @Disabled("Google has disabled this API")
  public void test() throws IOException {
    GoogleTimeZone googleTimeZone = JasonTimeZoneUtil.getTimeZone("39.6034810", "-119.6822510");
    Assertions.assertEquals("OK", googleTimeZone.getStatus());
  }

}
