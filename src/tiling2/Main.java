package tiling2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static final int Divisor = 1000000007;
	static int[] cache = new int[102];
	
	public static void main(String[] args) {
		InputReader input = new InputReader(System.in);
		
		int testCase = input.nextInt();
		
		while ( testCase-- > 0 ) {
			N = input.nextInt();
			
			System.out.println(countTile(N+1));
			
		}
	}
	
	private static int countTile(int n) {
		if ( n <= 1) {
			return n;
		}
		
		int result = cache[n];
		
		if ( result != 0 ) {
			return result;
		}
		
		return cache[n] = (countTile(n-1) + countTile(n-2)) % Divisor;
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
