package aroma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
/*

 */
	
	static int NUMBER_OF_AROMA;
	static int[] DURATION = new int[1000];
	static int[] TIME = new int[1000];
	static int ANSWER;
	
	public static void main(String[] args) {
		InputReader input = new InputReader(System.in);
		
		int testCase = input.nextInt();
		
		for (int i = 1; i <= testCase; i++) {
			NUMBER_OF_AROMA = input.nextInt();
			
			for (int j = 0; j < NUMBER_OF_AROMA; j++) {
				DURATION[j] = input.nextInt();
				TIME[j] = input.nextInt();
			}
			
			findMaximumDuration(0);
			
			System.out.println("#" + i + " " + ANSWER);
		}
	}
	
	private static void findMaximumDuration(int order) {
		for (int i = 0; i < NUMBER_OF_AROMA; i++) {
			if (i == order) {
				continue;
			}
			if (DURATION[i] < 0) {
				continue;
			}
			
			DURATION[i] = DURATION[i] - TIME[order];
			
			if (order < NUMBER_OF_AROMA) {
				findMaximumDuration(order+1);
			}
			
		}
	}

	static class InputReader {
		public BufferedReader reader;
		public StringTokenizer tokenizer;
	
		public InputReader(InputStream stream) {
			reader = new BufferedReader(new InputStreamReader(stream), 32768);
			tokenizer = null;
		}
		
		public String next() {
			while ( tokenizer == null || !tokenizer.hasMoreTokens() ) {
				try {
					tokenizer = new StringTokenizer(reader.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return tokenizer.nextToken();
		}
		
		public int nextInt() {
			return Integer.parseInt(next());
		}
	}
}
