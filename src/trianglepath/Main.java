package trianglepath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static int sizeOfTriangle;
	private static int[][] Triangle = new int[100][100];
	private static int[][] cache = new int[100][100];

	public static void main(String[] args) {
		InputReader input = new InputReader(System.in);
		int testCase = input.nextInt();
		while (testCase > 0) {
			sizeOfTriangle = input.nextInt();
			for (int i = 0; i < sizeOfTriangle; i++) {
				for (int j = 0; j <= i; j++) {
					Triangle[i][j] = input.nextInt();
					cache[i][j] = -1;
				}
			}

			List<Integer> sd = new ArrayList<>();
			sd.add(11);

			for (int i = 0; i < sd.size(); i++) {
				System.out.println(sd.get(i).intValue());
			}
			
			System.out.println(findMaxSum(0, 0));
			testCase--;
		}
	}
	
	private static int findMaxSum(int y, int x) {
		//삼각형 제일 하단으로 내려온 경우
		if (y == sizeOfTriangle - 1) {
			return Triangle[y][x];
		}
		
		//초기값일 경우
		if (cache[y][x] == -1) {
			cache[y][x] = Triangle[y][x];
		} else {
			return cache[y][x];
		}
		return cache[y][x] = Math.max(findMaxSum(y+1, x), findMaxSum(y+1, x+1)) + Triangle[y][x];
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
