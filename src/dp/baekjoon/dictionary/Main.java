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

	// ���� ����
	// 1256 DP, ���շ�
	
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
			int i, m = M; // n�� a�� �� m�� z�� ��
			
			// ��ü ���ڿ��� ���� �� �� ���� ���� Ȯ��
			for (i = 1; i <= L; i++) {
				// ���� ��ġ���� 'a'�� ���� ����� ������ Ŭ���
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
