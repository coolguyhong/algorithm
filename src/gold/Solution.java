package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int T, N, M;

	public static void main(String[] args) {
		InputReader input = new InputReader(System.in);
		
		T = input.nextInt();
		
		for (int testCase = 1; testCase <= T; testCase++) {
			N = input.nextInt();
			M = input.nextInt();
			
			int ans = M;
			for (int k = 1; k <= M; k++) {
				if (k*N % M == 0) {
					ans--;
				}
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
