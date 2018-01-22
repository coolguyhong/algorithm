package jlis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int[] arr1;
	private static int[] arr2;

	public static void main(String[] args) {
		InputReader input = new InputReader(System.in);
		
		int testCase = input.nextInt();
		while (testCase > 0) {
			arr1 = new int[input.nextInt()];
			arr2 = new int[input.nextInt()];
			
			for (int i = 0; i < arr1.length; ++i) {
				arr1[i] = input.nextInt();
			}
			
			for (int j = 0; j < arr2.length; ++j) {
				arr1[j] = input.nextInt();
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
			while (tokenizer == null || !tokenizer.hasMoreTokens()) {
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
