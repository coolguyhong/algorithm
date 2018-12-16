package dp.baekjoon.dictionary;

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
	
	private static final int MAXK = 1000000000;
	private static int N, M;
	private static long K;
	private static long[][] ncr = new long[201][201];

	// 백준 사전
	// 1256 DP, 조합론
	
	public static void main(String[] args) throws IOException {
		ncr[0][0] = 1;
		for (int i = 1; i <= 200; i++) {
			ncr[i][0] = 1;
			for (int j = 1; j <= i; j++) {
				ncr[i][j] = ncr[i-1][j] + ncr[i-1][j-1];
				if (ncr[i][j] > MAXK) {
					ncr[i][j] = MAXK+1;
				}
			}
		}
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Long.parseLong(st.nextToken());
		
		int L = N+M;
		if (K > ncr[L][M]) {
			bw.write("-1");
		} else {
			int i, m = M; // n은 a의 수 m은 z의 수
			
			// 전체 문자열의 길이 중 앞 문자 부터 확정
			for (i = 1; i <= L; i++) {
				// 현재 위치에서 'a'가 오는 경우의 수보다 클경우
				if (ncr[L-i][m] < K) { // z
					bw.write("z");
					K -= ncr[L-i][m];
					m--;
				} else { // a
					bw.write("a");
				}
			}
		}
		bw.flush();
		bw.close();
	}
}
