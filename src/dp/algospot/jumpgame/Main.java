package dp.algospot.jumpgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int n;
	private static int[][] board = new int[100][100];
	private static Boolean[][] cache = new Boolean[100][100];

	public static void main(String[] args) {
		InputReader input = new InputReader(System.in);
		int testCase = input.nextInt();
		while (testCase-- > 0) {
			n = input.nextInt();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					board[i][j] = input.nextInt();
					cache[i][j] = null;
				}
			}

			if (jump(0,0)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
		}
	}

    private static boolean jump(int y, int x) {
	    // 보드에 벗어 났을 경우
	    if (y >= n || x >= n) {
	        return false;
        }

        // 보드 끝에 왔을 경우
        if (y == n - 1 && x == n - 1) {
            return true;
        }

        if (cache[y][x] == null) {
            int size = board[y][x];
            cache[y][x] = jump(y, x + size) || jump(y + size, x);
        }

        return cache[y][x];
    }
}

class InputReader {
	BufferedReader reader;
	StringTokenizer tokenizer;

	InputReader(InputStream stream) {
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
