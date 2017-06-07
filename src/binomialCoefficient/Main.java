package binomialCoefficient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int cache[][] = new int[30][30];
	
	public static void main(String[] args) {
		InputReader input = new InputReader(System.in);
		
		final int n = input.nextInt();
		final int r = input.nextInt();
		
		System.out.println(bino(n, r));
	}

	private static int bino(int n, int r) {
		if (r == 0 || n == r) {
			return 1;
		}
		
		if (cache[n][r] != 0) {
			return cache[n][r];
		}
		
		return cache[n][r] = bino(n-1, r-1) + bino(n-1, r);
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
