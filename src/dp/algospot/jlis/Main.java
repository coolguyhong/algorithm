package dp.algospot.jlis;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	
	private static int[] arr1 = new int[100];
	private static int[] arr2 = new int[100];
	private static int n,m;
	private static int[] jlis = new int[200];

	public static void main(String[] args) {
		InputReader input = new InputReader(System.in);

		int testCase = input.nextInt();
		while (testCase > 0) {
			n = input.nextInt();
			m = input.nextInt();
			arr1 = new int[n];
			arr2 = new int[m];
			
			for (int i = 0; i < arr1.length; ++i) {
				arr1[i] = input.nextInt();
			}
			
			for (int j = 0; j < arr2.length; ++j) {
				arr1[j] = input.nextInt();
			}
			System.out.println(arr1[0]);
			jlis();
			testCase--;
			
		}
	}

	private static void jlis() {
		int max = 1;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (jlis[0] == 0) {

				}
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
