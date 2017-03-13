package boggle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static char[][] Boggle = new char[5][5];
	static int N;
	static int[] dx = {-1, -1, -1, 1, 1, 1, 0, 0};
	static int[] dy = {-1, 0, 1, -1, 0, 1, -1, 1};
	
	public static void main(String[] args) {
		InputReader input = new InputReader(System.in);
		
		int testCase = input.nextInt();
		
		while ( testCase-- > 0 ) {
			for ( int i = 0 ; i < 5 ; ++i ) {
				String boggleInput = input.next();
				for ( int j = 0 ; j < 5 ; ++j ) {
					Boggle[i][j] = boggleInput.charAt(j);
				}
			}
			
			N = input.nextInt();
			
			for ( int i = 0 ; i < N ; i++ ) {
				String voca = input.next();
				findLocate(voca);
			}
			
		}
	}

	private static void findLocate(String voca) {
		for ( int i = 0 ; i < 5 ; i++ ) {
			for ( int j = 0 ; j < 5 ; j++ ) {
				if ( Boggle[i][j] == voca.charAt(0) ) {
					hasWord(j, i, voca.charAt(1));
				}
			}
		}
	}

	private static void hasWord(int y, int x, char charAt) {
		
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
