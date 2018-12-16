package search.baekjoon.sumofnum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, M;
	private static int[] A;

	// 백준 알고리즘
	// 수들의 합 2(2003 번) : 투 포인터
	public static void main(String[] args) throws IOException {
		InputReader input = new InputReader(System.in);
		
		N = input.nextInt();
		M = input.nextInt();
		
		A = new int[N+1];
		for (int i = 1; i <= N; i++) {
			A[i] = input.nextInt();
		}
		
		int ans = 0;
		int right = 0;
		int sum = 0;
		for (int i = 1; i <= N; i++) {
			while (right < N && sum < M) {
				sum += A[++right];
			}
			
			if (sum == M) {
				ans++;
			}
			
			sum -= A[i];
		}
		
		System.out.println(ans);
	}
	
	static class InputReader {
		public BufferedReader br;
		public StringTokenizer st;
		
		public InputReader(InputStream stream) {
			br = new BufferedReader(new InputStreamReader(stream), 32768);
			st = null;
		}
		
		public String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		public int nextInt() {
			return Integer.parseInt(next());
		}
	}
}
