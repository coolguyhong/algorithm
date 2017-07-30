package lis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static int[] SEQUENCE = new int[500];
	private static int Lengh_Sequence;
	private static int Max_Length;
	private static List<Integer> lisList = new ArrayList<>();

	public static void main(String[] args) {
		InputReader input = new InputReader(System.in);
		int testCase = input.nextInt();
		while (testCase > 0) {
			Lengh_Sequence = input.nextInt();
			for (int i = 0; i < Lengh_Sequence; i++) {
				SEQUENCE[i] = input.nextInt();
			}
			System.out.println(findLIS(SEQUENCE[0], 0));
			Max_Length = 0;
			lisList.clear();
			testCase--;
		}
	}

	private static int findLIS(int value, int location) {
		if (value == 0 || location == SEQUENCE.length) {
			return Max_Length;
		}

		if (location == 0) {
			lisList.add(value);
			Max_Length = lisList.size();
			location = location + 1;
			findLIS(SEQUENCE[location], location);
		}

		if (value > lisList.get(Max_Length - 1)) {
			lisList.add(value);
			Max_Length = lisList.size();
			location = location + 1;
			findLIS(SEQUENCE[location], location);
		} else {
			location = location + 1;
			findLIS(SEQUENCE[location], location);
		}

		return Max_Length;
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