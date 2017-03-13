package picnic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int m;
	static boolean[][] areFriends;
	static boolean[] taken;
	
	public static void main(String[] args) {
		InputReader input = new InputReader(System.in);
		
		int testCase = input.nextInt();
		
		while ( testCase-- > 0 ) {
			n = input.nextInt();
			areFriends = new boolean[n][n];
			taken = new boolean[n];
			
			m = input.nextInt();
			for ( int i = 0 ; i < m ; i++ ) {
				int x = input.nextInt();
				int y = input.nextInt();
				
				areFriends[x][y] = true;
				areFriends[y][x] = true;
			}
			
			System.out.println(countCouple());
		}
		
	}
	
	private static int countCouple() {
		int result = 0;
		int firstFriend = -1;
		
		boolean finished = true;
		
		for ( int i = 0 ; i < n ; i++ ) {
			if ( !taken[i] ) {
				firstFriend = i;
				finished = false;
				break;
			}
		}
		
		if ( finished ) {
			return 1;
		}
		
		for ( int pairFriend = firstFriend + 1 ; pairFriend < n ; ++pairFriend ) {
			if ( !taken[pairFriend] && areFriends[firstFriend][pairFriend] ) {
				taken[firstFriend] = true;
				taken[pairFriend] = true;
				result += countCouple();
				taken[firstFriend] = false;
				taken[pairFriend] = false;
				
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
