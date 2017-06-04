package pi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static final int X = 1000000;
	private static int T, N, M;
	private static int[] P;
	private static long[] S;
	
	public static void main(String[] args) {
		P = new int[X+1];
		S = new long[X+1];
		
		for (int i = 1; i <= X; i++) {
			P[i] = i;
		}
		
		for (int i = 2; i <= X; i++) {
			if (P[i] == i) {
				for (int j = 1; j <= X; j+=i) {
					P[j] = P[j] - P[j]/i;
				}
			}
		}
		
		for (int i = 1; i <= X; i++) {
			S[i] = S[i-1] + P[i];
		}
		
		InputReader input = new InputReader(System.in);
		
		T = input.nextInt();
		for (int testCase = 1; testCase <= T; testCase++) {
			int l = input.nextInt();
			int r = input.nextInt();
			System.out.println("#" + testCase + " " + (S[r] - S[l-1]));
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
