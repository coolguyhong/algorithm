package trianglepath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int sizeOfTriangle;
	private static int[][] Triangle = new int[100][100];

	public static void main(String[] args) {
		InputReader input = new InputReader(System.in);
		int testCase = input.nextInt();
		while (testCase > 0) {
			sizeOfTriangle = input.nextInt();
			for (int i = 0; i < sizeOfTriangle; i++) {
				for (int j = 0; j <= i; j++) {
					Triangle[i][j] = input.nextInt();
				}
			}
			
			System.out.println(findMaxSum(0, 0, 0));
			testCase--;
		}
	}
	
	private static int findMaxSum(int y, int x, int tmpSum) {
		//삼각형 밖으로 벗어난 경우
		if (y >= sizeOfTriangle || x >= sizeOfTriangle) {
			return 0;
		}
		
		//삼각형 제일 하단으로 내려온 경우
		if (y == sizeOfTriangle-1) {
			return tmpSum + Triangle[y][x];
		}
		
		if (tmpSum == 0) {
			tmpSum = Triangle[0][0];
		} else {
			tmpSum += Triangle[y][x];
		}
		
		return Math.max(findMaxSum(y+1, x, tmpSum), findMaxSum(y+1, x+1, tmpSum));
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
