package org.jason.util.algorithm;

import java.util.ArrayList;
import java.util.List;


public class FabonacciUtil {

    /**
     * Generate a series of Fabonacci list.
     *
     * @param size the size of the list
     * @return A fabonacci List
     */
    public static List<Integer> getFabonacciSeries(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("index must be positive numbers");
        }
        List<Integer> result = new ArrayList<Integer>(size);
        for (int index = 0; index < size; index++) {
            result.add(getFabonacciNumber(index + 1));
        }

        return result;
    }

    /**
     * Get a Fabonacci number.
     *
     * @param index the index of the fabonacci number.
     * @return the fabonacci number.
     */
    public static int getFabonacciNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("index must be positive numbers");
        }

        if (number == 1 || number == 2) {
            return 1;
        }

        return getFabonacciNumber(number - 1) + getFabonacciNumber(number - 2);
    }
}
