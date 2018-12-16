package dp.baekjoon.binomial_coefficient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;
	private static int N, K;
	private static int[][] C = new int[1001][1001];
	private static final int mod = 10007;

	// 백준 이항계수2
	// 11051
	public static void main(String[] args) throws IOException {
		C[0][0] = 1;
		for (int i = 1; i <= 1000; i++) {
			for (int j = 0; j <= 1000; j++) {
				C[i][j] = (C[i-1][j] + (j > 0 ? C[i-1][j-1] : 0)) % mod;
			}
		}
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		bw.write(C[N][K] + "\n");
		bw.flush();
		bw.close();
	}

}
