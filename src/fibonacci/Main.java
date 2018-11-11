package fibonacci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N;
	private static long[] D;
	public static void main(String[] args) {

		InputReader input = new InputReader(System.in);
		
		N = input.nextInt();
		D = new long[N+1];
		D[0] = 0;
		D[1] = 1;
		for (int i = 2; i <= N; i++) {
			D[i] = D[i-1] + D[i-2];
		}
		
		System.out.println(D[N]);
		
	}

	static class InputReader {
		public BufferedReader br;
		public StringTokenizer st;
		
		public InputReader(InputStream stream) {
			br = new BufferedReader(new InputStreamReader(stream), 32768);
			st = null;
		}
		
		public String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		public int nextInt() {
			return Integer.parseInt(next());
		}
	}
}
