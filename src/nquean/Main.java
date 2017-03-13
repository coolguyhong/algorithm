package nquean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int boardSize = 8;
	static int solCnt;
	static int[] sol = new int[boardSize];
	static int[] queanCol = new int[boardSize];
	
	public static void main(String[] args) {
		solCnt = 0;
		backtrack(0);
		System.out.println(solCnt);
	}
	
	private static void backtrack(int row) {
		if ( isSolution(row) ) {
			processSolution();
			return;
		}
		
		loop : for ( int i = 0 ; i < boardSize ; i++ ) {
			if ( queanCol[i] == 1 ) {
				continue loop;
			}
			
			//대각선 방향에 퀸이 존재하면 가지치기
			for ( int j = 0; j < row; j++ ) {
				if ( Math.abs(row - j) == Math.abs(i - sol[j]) ) {
					continue loop;
				}
			}
			
			sol[row] = i;
			
			queanCol[i] = 1;
			
			backtrack(row + 1);
			
			queanCol[i] = 0;
		}
	}

	private static void processSolution() {
		solCnt++;
	}

	private static boolean isSolution(int row) {
		return (row == boardSize);
	}

	class InputReader {
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
