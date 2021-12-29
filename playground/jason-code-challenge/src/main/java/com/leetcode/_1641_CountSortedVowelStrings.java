package com.leetcode;
/**
 * Leetcode 1641 - Count Sorted Vowel Strings
 * 
 * Description - https://leetcode.com/problems/count-sorted-vowel-strings/
 * 
 * Key - Dynamic Programming + recursive - 
 *     - countVowelStrings (n, 5) = countVowelStrings(n - 1, 5) + countVowelStrings(n, 4)
 *     - Best - (n+4)*(n+3)*(n+2)*(n+1)*(n+4)/(1*2*3*4)
 * @author jason
 *
 */
public class _1641_CountSortedVowelStrings {
	
	public int countVowelStrings(int n) {
		
		return countVowelStrings(n, 5);
	}
	
    private int countVowelStrings(int n, int vowels){
    	
    	if (n == 1) return vowels;
    	
    	if (vowels == 1) return 1;
    	
        return countVowelStrings(n-1, vowels) + countVowelStrings(n, vowels-1);
    }

    // Best
    public int countVowelStrings_best(int n) {
        return (n + 4) * (n + 3) * (n + 2) * (n + 1) / 24;
    }
}
