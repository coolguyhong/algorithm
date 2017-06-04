package hanoi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int T, N;
	private static int[] A;
	

	public static void main(String[] args) {
		InputReader input = new InputReader(System.in);
		
		T = input.nextInt();
		for (int testCase = 1; testCase <= T; testCase++) {
			N = input.nextInt();
			A = new int[N+1];
			
			for (int i = 1; i <= N; i++) {
				A[i] = input.nextInt();
			}
			
			long from = collect(N, 1);
			long to = collect(N, 2);
			
			String answer;
			if (from+to == (1L << N) - 1) {
				answer = "yes";
			} else {
				answer = "no";
			}
			System.out.println("#" + testCase + " " + answer);
		}
	}

	private static long collect(int n, int t) {
		if (n == 0) {
			return 0;
		}
		
		if (A[n] == t) {
			return collect(n-1, t);
		}
		
		int other = 6 - A[n] - t;
		return collect(n-1, other) + (1L << (n-1));
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
