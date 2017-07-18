package jumpgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int n;
	private static int board[][] = new int[100][100];
	private static boolean cache[][] = new boolean[100][100];

	public static void main(String[] args) {
		InputReader input = new InputReader(System.in);
		long start = System.currentTimeMillis();

		final int testCase = input.nextInt();
		for (int i = 1; i <= testCase; i++) {
			n = input.nextInt();
			// board 만들기
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					cache[j][k] = false;
					board[j][k] = input.nextInt();
				}
			}
			
			if(jump(0, 0)) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
		long elapsedTime = System.currentTimeMillis() - start;
		System.out.println( "실행 시간 : " + elapsedTime +  " ms");
	}

	private static boolean jump(int y, int x) {
		//board 밖으로 벗어난 경우
		if (y >= n || x >= n) {
			return false;
		}
		
		//board에 도착했을 경우
		if (y == n-1 && x == n-1) {
			return true;
		}
		if (cache[y][x]) {
			return cache[y][x];
		}
		int jumpSize = board[y][x];
		return cache[y][x] = (jump(y + jumpSize, x) || jump(y, x + jumpSize));
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
