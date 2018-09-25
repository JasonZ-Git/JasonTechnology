package org.jason.util;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.math3.util.MathArrays;

/**
 * This Utility is designed to process common logic related to TFN.
 * 
 * @author jason.zhang
 */

public final class TFNUtil {

	private static final double[] factor = { 1, 4, 3, 7, 5, 8, 6, 9, 10};

	public static String generateTFN() {
		
		int[] randomInts = new Random().ints(9, 1, 10).toArray();
		
		if (isValidTFN(randomInts)) return Arrays.stream(randomInts).boxed().map(String::valueOf).collect(Collectors.joining());
		
		return generateTFN();
	}
	
	public static boolean isValidTFN(String inputTFN) {
		
		return isValidTFN(inputTFN.chars().toArray());
	}
	
	public static boolean isValidTFN(int[] inputTFN) {
		
		double[] inputArray  = Arrays.stream(inputTFN).mapToDouble(a -> (double)a).toArray();
		
		int result = (int)MathArrays.linearCombination(inputArray, factor);
		
		return result%11 == 0;
	}
	
}
