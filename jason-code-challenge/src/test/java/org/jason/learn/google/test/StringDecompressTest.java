package org.jason.learn.google.test;

import java.util.Arrays;
import java.util.Collection;

import org.jason.learn.google.StringDecompress;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class StringDecompressTest {

    @Parameter(0)
    public String source;
    @Parameter(1)
    public String result;

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{{"3[abc]4[ab]c", "abcabcabcababababc"}, {"2[3[a]b]", "aaabaaab"},
                {"10[a]", "aaaaaaaaaa"}};

        return Arrays.asList(data);
    }

    @Test
    public void test() {
        String actual = StringDecompress.decompress(source);

        Assert.assertEquals(result, actual);
    }
}
