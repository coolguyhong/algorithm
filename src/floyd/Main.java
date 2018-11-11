package floyd;

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

	private static int N, M;
	private static int[][] D;
	private static final int MAX_VALUE = 10000001;
	
	// 플로이드 알고리즘
	// 백준: 플로이드(11404)
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		D = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j) {
					D[i][j] = 0;
				} else {
					D[i][j] = MAX_VALUE;
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		
		int a, b, c;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			D[a][b] = Math.min(D[a][b], c);
		}
		
		floyd();
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (D[i][j] == MAX_VALUE || i == j) {
					bw.write(0 + " ");
				} else {
					bw.write(D[i][j] + " ");
				}
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}

	private static void floyd() {
		for (int k = 1; k <= N; k++) {
			for (int s = 1; s <= N; s++) {
				for (int e = 1; e <= N; e++) {
					if (D[s][e] > D[s][k] + D[k][e]) {
						D[s][e] = D[s][k] + D[k][e];
					}
				}
			}
		}
	}
}
