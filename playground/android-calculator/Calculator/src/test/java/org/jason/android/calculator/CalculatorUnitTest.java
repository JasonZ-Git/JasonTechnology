package org.jason.android.calculator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
@RunWith(MockitoJUnitRunner.class)
public class CalculatorUnitTest {
    @Mock
    private CalculatorModulo7Proxy calculator;

    @Mock
    private System system;

    @Before
    public void init() {
        // when(system.loadLibrary("calculator")).thenReturn();
    }

    @Test
    public void additionTest() {
        when(calculator.add(0, 3)).thenReturn(3);
        when(calculator.add(2, 4)).thenReturn(6);
        when(calculator.add(0, 3)).thenReturn(3);
        assertEquals(3, calculator.operate(OperationType.Add, 0, 3));
        assertEquals(6, calculator.operate(OperationType.Add, 2, 4));
        assertEquals(12, calculator.operate(OperationType.Add, 6, 3));
    }


    @Test
    public void subtractionTest() {
        assertEquals(-3, calculator.operate(OperationType.Sub, 0, 3));
        assertEquals(2, calculator.operate(OperationType.Sub, 4, 2));
        assertEquals(4, calculator.operate(OperationType.Sub, 10, 3));
    }

    @Test
    public void multipationTest() {
        assertEquals(0, calculator.operate(OperationType.Mul, 0, 3));
        assertEquals(11, calculator.operate(OperationType.Mul, 4, 2));
        assertEquals(30, calculator.operate(OperationType.Mul, 10, 3));
    }
}