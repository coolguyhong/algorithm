package cave;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int T, N, H;
	private static int[] S;

	public static void main(String[] args) {
		InputReader input = new InputReader(System.in);
		
		T = input.nextInt();
		
		for (int ts = 1; ts <=T; ts++) {
			N = input.nextInt();
			H = input.nextInt();
			S = new int[H+2];
			for (int i = 1; i < N; i++) {
				int h = input.nextInt();
				if (i % 2 == 1) {
					S[1]++;
					S[h+1]--;
				} else {
					S[H-h+1]++;
					S[H+1]--;
				}
			}
			
			for (int i = 2; i <= H+1; i++) {
				S[i] += S[i-1];
			}
			
			int ans = Integer.MAX_VALUE;
			int cnt = 0;
			for (int i = 1; i <=H; i++) {
				if (ans > S[i]) {
					ans = S[i];
					cnt = 1;
				} else if (ans == S[i]) {
					cnt++;
				}
			}
			
			System.out.println("#" + ts + " " + ans + " " + cnt);
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
