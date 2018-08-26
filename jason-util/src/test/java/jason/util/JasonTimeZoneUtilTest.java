package jason.util;

import java.io.IOException;

import org.jason.util.JasonTimeZoneUtil;
import org.jason.util.object.GoogleTimeZone;
import org.junit.Assert;
import org.junit.Test;

public class JasonTimeZoneUtilTest {

	@Test
	public void test() throws IOException {
		GoogleTimeZone googleTimeZone =  JasonTimeZoneUtil.getTimeZone("39.6034810", "-119.6822510");
		Assert.assertEquals("OK", googleTimeZone.getStatus());
		Assert.assertEquals("America/Los_Angeles", googleTimeZone.getTimeZoneId());
	}

}
