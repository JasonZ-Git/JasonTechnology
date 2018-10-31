package crack_code_interview_test;

import org.junit.Assert;
import org.junit.Test;
import crack_code_interview.Q1_8_Zero_Matrix;

public class Q1_8_Zero_Matrix_Test {

    @Test
    public void testZeroMatrix() {
        int[][] input = new int[][] { { 1, 2, 0, 4 }, { 5, 6, 7, 8 }, { 0, 10, 11, 12 }, { 13, 14, 15, 16 } };
        int[][] expected = new int[][] { { 0, 0, 0, 0 }, { 0, 6, 0, 8 }, { 0, 0, 0, 0 }, { 0, 14, 0, 16 } };

        int[][] actual = Q1_8_Zero_Matrix.toZeroMatrix(input);

        Assert.assertArrayEquals(expected, actual);
    }
}
