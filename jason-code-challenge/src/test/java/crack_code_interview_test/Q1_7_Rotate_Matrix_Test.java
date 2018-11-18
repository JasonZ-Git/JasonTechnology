package crack_code_interview_test;

import org.jason.code_practice.Q1_7_Rotate_Matrix;
import org.junit.Assert;
import org.junit.Test;

public class Q1_7_Rotate_Matrix_Test {

    @Test
    public void testFlip() {
        int[][] input = new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
        int[][] expected = new int[][] { { 1, 5, 9, 13 }, { 2, 6, 10, 14 }, { 3, 7, 11, 15 }, { 4, 8, 12, 16 } };

        int[][] actual = Q1_7_Rotate_Matrix.flipMatrix(input);

        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void testOneDimention() {
        int[][] input = new int[][] { { 1 } };
        int[][] expected = new int[][] { { 1 } };

        int[][] actual = Q1_7_Rotate_Matrix.flipMatrix(input);

        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void testRotate() {
        int[][] input = new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
        int[][] expected = new int[][] { { 13, 9, 5, 1 }, { 14, 10, 6, 2 }, { 15, 11, 7, 3 }, { 16, 12, 8, 4 } };

        int[][] actual = Q1_7_Rotate_Matrix.rotateMatrix(input);

        Assert.assertArrayEquals(expected, actual);
    }
}
