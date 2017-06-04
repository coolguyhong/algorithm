package euclid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int T, X, Y, Z;
	
	public static void main(String[] args) {
		InputReader input = new InputReader(System.in);
		
		T = input.nextInt();
		for (int testCase = 1; testCase <= T; testCase++) {
			System.out.println("#" + testCase + " ");
			X = input.nextInt();
			Y = input.nextInt();
			Z = input.nextInt();
			
			int g = gcd(X, Y);
			if (Z % g != 0) {
				System.out.println(-1);
				continue;
			}
			
			long[] v = ee(X, Y);
			int sc = Z / g;
			System.out.println(v[0] * sc + " " + v[1] * sc);
		}
	}

	private static long[] ee(long a, long b) {
		long[] ret = new long[2];
		
		if (b == 0) {
			ret[0] = 1;
			ret[1] = 0;
			return ret;
		}
		
		long g = a/b;
		long[] v = ee(b, a%b);
		
		ret[0] = v[1];
		ret[1] = v[0] - v[1]*g;
		
		return ret;
	}

	private static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a%b);
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
