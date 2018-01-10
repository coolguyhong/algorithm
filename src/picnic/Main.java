package picnic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static boolean areFriends[][];
	private static int students;
	private static boolean[] taken;

	public static void main(String[] args) {
		InputReader input = new InputReader(System.in);
		
		int testCase = input.nextInt();
		while (testCase > 0) {
			students = input.nextInt();
			final int pair = input.nextInt();
			taken = new boolean[students];
			areFriends = new boolean[students][students];
			for (int i = 0; i < pair; i++) {
				int x = input.nextInt();
				int y = input.nextInt();
				areFriends[x][y] = true;
				areFriends[y][x] = true;
			}
			
			System.out.println(matchingFriends(0));
			testCase--;
		}
		
	}
	
	private static int matchingFriends(int seq) {
		// Find the fastest student in the list 
		int firstFree = -1;
		for (int i = 0; i < students; ++i) {
			if (!taken[i]) {
				firstFree = i;
				break;
			}
		}
		
		// all students find their own pairs and finish recursion
		if (firstFree == -1) {
			return 1;
		}
		
		int result = 0;
		for (int pairWith = firstFree + 1; pairWith < students; ++pairWith) {
			if (!taken[pairWith] && areFriends[firstFree][pairWith] && areFriends[pairWith][firstFree]) {
				taken[firstFree] = true;
				taken[pairWith] = true;
				result += matchingFriends(firstFree + 1);
				taken[firstFree] = false;
				taken[pairWith] = false;
			}
		}
		return result;
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
