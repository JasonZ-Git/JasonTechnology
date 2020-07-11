package jason.util;

import java.io.IOException;
import java.util.List;

import org.jason.util.JasonFileUtil;
import org.junit.Test;

import junit.framework.Assert;

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

        Assert.assertEquals(2, lines.size());
        Assert.assertEquals("Hello", lines.get(0));
        Assert.assertEquals("World", lines.get(1));
    }
}
