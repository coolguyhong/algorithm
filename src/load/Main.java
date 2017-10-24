package load;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static int[] VISITED = new int[1001];
	private static int N;
	private static int M;
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

			dfs(1, 0, 1);
			System.out.print("#" + testCase + " " + ANSWER.size()+ " ");
			for (Integer ans : ANSWER) {
				System.out.print(ans + " ");
			}
			System.out.println();
			ANSWER.clear();
		}
	}

	private static void dfs(int h, int p, int d) {
		VISITED[h] = d;
		for (int i = 1; i <= N; ++i) {
			if (ROAD[h][i] == p) {
				continue;
			}
			if (VISITED[i] != 0) {
				dfs(i, h, d + 1);
				if (VISITED[i] == d + 1) {
					ANSWER.add(ROAD[h][i]);
				}
			}
			VISITED[h] = Math.min(VISITED[h], VISITED[i]);
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