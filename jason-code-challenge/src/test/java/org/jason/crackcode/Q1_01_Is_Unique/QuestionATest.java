package org.jason.crackcode.Q1_01_Is_Unique;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class QuestionATest {
    
    @Parameter(0)
    public String toTest;
    
    @Parameter(1)
    public boolean result;
    
    @Parameters
    public static Collection<Object[]> data() {
        Object[][] parameters = new Object[][] {/*{"abcdfgt", true}, {"abcdefgabc", false}, {"1234567890abcdefghijklmnopqrst~!@#$%^&*()_+|}{[]", true}, */{"这个", true}};
        
        return Arrays.asList(parameters);
    }
    
    @Test
    public void testIsUnique() {
        boolean resultOfA = QuestionA.isUniqueASCII(toTest);
        
        Assert.assertEquals(result, resultOfA);
    }
    
    @Test
    public void testIsUnique_with_set() {
        boolean resultOfA = QuestionA.isUniqueChars_with_set(toTest);
        
        Assert.assertEquals(result, resultOfA);
    }
    
    @Test
    public void testIsUnique_with_stream() {
        boolean resultOfA = QuestionA.isUniqueChars_with_stream(toTest);
        
        Assert.assertEquals(result, resultOfA);
    }
}
