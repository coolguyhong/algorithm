package dp.algospot.lis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[] SEQUENCE = new int[500];
	private static int[] LIS = new int[500];
	private static int length;

	public static void main(String[] args) {
		InputReader input = new InputReader(System.in);
		int testCase = input.nextInt();
		while (testCase > 0) {
			length = input.nextInt();
			for (int i = 0; i < length; i++) {
				SEQUENCE[i] = input.nextInt();
			}
			lis();
			testCase--;
		}
	}

	private static void lis() {
		int max = 1;
		LIS[0] = 1;

		for (int i = 1; i < length; i++) {
			LIS[i] = 1;

			for (int j = 0; j < i; j++) {
				if (SEQUENCE[i] > SEQUENCE[j] && LIS[j] + 1 > LIS[i]) {
					LIS[i] = LIS[j] + 1;
				}
			}

			if (max < LIS[i]) {
				max = LIS[i];
			}
		}

		System.out.println(max);
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