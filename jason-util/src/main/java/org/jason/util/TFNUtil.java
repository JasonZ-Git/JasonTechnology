package org.jason.util;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import org.apache.commons.math3.util.MathArrays;

public final class TFNUtil {

	private static final double[] factor = { 1, 4, 3, 7, 5, 8, 6, 9, 10};
	
	public static String generate() {
		
		int[] randomInts = new Random().ints(9, 1, 10).toArray();
		
		if (isValidTFN(randomInts)) return Arrays.toString(randomInts);
		
		return generate();
	}
	
	
	public static boolean isValidTFN(String inputTFN) {
		
		return isValidTFN(inputTFN.chars().toArray());
	}
	
	public static boolean isValidTFN(int[] inputTFN) {
		
		double[] inputArray  = Arrays.stream(inputTFN).mapToDouble(a -> (double)a).toArray();
		
		int result = (int)MathArrays.linearCombination(inputArray, factor);
		
		return result%11 == 0;
	}
	
	
	public static void main(String[] args) {
		
		IntStream.range(1, 20).mapToObj(i -> generate()).forEach(System.out::println);
	}
}
