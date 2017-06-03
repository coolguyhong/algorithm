package kindergarten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	private static int T, N, K;
	private static int[] A, B;
	
	public static void main(String[] args) {
		InputReader input = new InputReader(System.in);
		
		T = input.nextInt();
		for (int testCase = 1; testCase <= T; testCase++) {
			N = input.nextInt();
			K = input.nextInt();
			
			A = new int[N+1];
			for (int i = 1; i <= N; i++) {
				A[i] = input.nextInt();
			}
			
			B = new int[N];
			for (int i = 1; i < N; i++) {
				B[i] = A[i+1] - A[i];
			}
			Arrays.sort(B, 1, N);
			
			int ans = A[N] - A[1];
			
			for (int i = 1; i < K; i++) {
				ans -= B[N-i];
			}
			
			System.out.println("#" + testCase + " " + ans);
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
