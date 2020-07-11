package crack_code_interview_test;

import java.util.Arrays;
import java.util.Collection;

import org.jason.code_practice.Q1_5_One_Way;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class Q1_5_One_Way_Test {

    @Parameter(0)
    public String first;
    @Parameter(1)
    public String second;
    @Parameter(2)
    public boolean result;

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {"pale", "ple", true}, {"pales", "pale", true}, {"pale", "bale", true}, {"pale", "bake", false}
        };

        return Arrays.asList(data);
    }

    @Test
    public void test() {
        boolean actual = Q1_5_One_Way.isOneWay(first, second);

        Assert.assertEquals(result, actual);
    }
}
