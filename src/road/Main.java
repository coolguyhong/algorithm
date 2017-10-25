package road;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static int[] DISCOVERED = new int[1001];
	private static int N;
	private static int M;
	private static int SEQUENCE = 0;
	private static int[][] ROAD = new int[1001][1001];
	private static List<Integer> ANSWER = new ArrayList<>();

	public static void main(String[] args) {
		InputReader input = new InputReader(System.in);

		int testCase = input.nextInt();
		for (int i = 1; i <= testCase; ++i) {
			N = input.nextInt();
			M = input.nextInt();
			for (int j = 1; j <= M; ++j) {
				int x = input.nextInt();
				int y = input.nextInt();
				ROAD[x][y] = j;
			}

			dfs(1, 0);

			System.out.print("#" + i + " " + ANSWER.size() + " ");
			if (!ANSWER.isEmpty()) {
				Collections.sort(ANSWER);
				for (Integer ans : ANSWER) {
					System.out.print(ans + " ");
				}
				ANSWER.clear();
			}
			SEQUENCE = 0;
			System.out.println();
		}
	}

	private static int dfs(int current, int par) {
		DISCOVERED[current] = ++SEQUENCE;
		int ret = DISCOVERED[current];

		for (int i = 1; i <= N; ++i) {
			if (i == par || ROAD[current][i] == 0) {
				continue;
			}

			if (DISCOVERED[i] == 0) {
				int df = dfs(i, current);
				if (df > DISCOVERED[current]) {
					ANSWER.add(ROAD[current][i]);
				}
				ret = Math.min(ret, df);
			} else {
				ret = Math.min(ret, DISCOVERED[i]);
			}
		}
		return ret;
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