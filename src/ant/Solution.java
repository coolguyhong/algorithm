package ant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution {
	private static int T, N, K, L;
	private static Ant[] A;
	private static Falling[] B;

	public static void main(String[] args) {
		InputReader input = new InputReader(System.in);
		
		T = input.nextInt();
		
		for (int ts = 1; ts <= T; ts++) {
			N = input.nextInt();
			K = input.nextInt();
			L = input.nextInt();
			A = new Ant[N+1];
			B = new Falling[N+1];
			
			for (int i = 1; i <= N; i++) {
				int x = input.nextInt();
				char dir = input.next().charAt(0);
				
				A[i] = new Ant(x, dir, i);
				if (dir == 'L') {
					B[i] = new Falling(x, dir);
				} else {
					B[i] = new Falling(L - x, dir);
				}
			}
			
			Arrays.sort(A, 1, N+1, new Comparator<Ant>() {
				public int compare(Ant a, Ant b) {
					return a.x - b.x;
				}
			});
			
			Arrays.sort(B, 1, N+1, new Comparator<Falling>() {
				public int compare(Falling a, Falling b) {
					if (a.time != b.time) {
						return a.time - b.time;
					} else {
						return a.dir - b.dir;
					}
				}
			});
			
			int l = 1;
			int r = N;
			
			for (int i = 1; i < K; i++) {
				if (B[i].dir == 'L') {
					l++;
				} else {
					r--;
				}
			}
			
			int num = 0;
			if (B[K].dir == 'L') {
				num = A[l].num;
			} else {
				num = A[r].num;
			}
			
			System.out.println("#" + ts + " " + num + " " + B[K].time);
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
