package crack_code_interview_test;

import java.util.Arrays;
import java.util.Collection;

import org.jason.code_practice.Q1_6_String_Compression;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class Q1_6_StringCompressionTest {
    @Parameter(0)
    public String original;

    @Parameter(1)
    public String compressed;

    @Parameters
    public static Collection<Object[]> date() {
        Object[][] data = new Object[][]{{"aabccccaaa", "a2b1c4a3"}, {"abcVcDBa", "abcVcDBa"}};
        return Arrays.asList(data);
    }

    @Test
    public void testCompress() {
        String actual = Q1_6_String_Compression.compress(original);

        Assert.assertEquals(compressed, actual);
    }
}
