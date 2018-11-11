package bridge;

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
	private static int[][] C = new int[31][31];
	
	// 백준 다리연결
	// 1010 조합
	public static void main(String[] args) throws IOException {
		C[0][0] = 1;
		for (int i = 1; i <= 30; i++) {
			for (int j = 0; j <= 30; j++) {
				C[i][j] = C[i-1][j] + (j > 0 ? C[i-1][j-1] : 0);
			}
		}
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			bw.write(C[M][N] + "\n");
		}
		bw.flush();
		bw.close();
	}
}
