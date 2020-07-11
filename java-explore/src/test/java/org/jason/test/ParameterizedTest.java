package org.jason.test;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class ParameterizedTest {

    private String numberA;
    private boolean expected;

    // Inject via constructor
    // for {8, 2, 10}, numberA = 8, numberB = 2, expected = 10
    public ParameterizedTest(String numberA, boolean expected) {
        this.numberA = numberA;
        this.expected = expected;
    }

    // name attribute is optional, provide an unique name for test
    // multiple parameters, uses Collection<Object[]>
    @Parameters(name = "{index}: length of {0} is {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{{"hello", true}, {"world", true}, {"a", false}});
    }

    @Test
    public void test_addTwoNumbes() {
        Assert.assertEquals(numberA.length() > 3, expected);
    }

}
