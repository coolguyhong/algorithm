package dp.algospot.jlis;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;
	
	private static int[] arrN, arrM;
	private static int n, m;
	private static int[][] D; // arrN, arrM의 각각 idx에서 얻을 수 있는 증가수열의 최고 길이

	// 알고스팟 문제해결전략
	// https://www.algospot.com/judge/problem/read/JLIS
	// 합친 jlis dp
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int testCase = Integer.parseInt(br.readLine());
		while (testCase-- > 0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			arrN = new int[n+1];
			arrM = new int[m+1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) {
				arrN[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= m; i++) {
				arrM[i] = Integer.parseInt(st.nextToken());
			}

			D = new int[n+2][m+2];

			bw.write((jlis(0, 0) - 2) + "\n" );
		}
		bw.close();
	}

	private static int jlis(int idxN, int idxM) {
		if (D[idxN+1][idxM+1] != 0) {
			return D[idxN+1][idxM+1];
		}

		// 최소 길이 arrN, arrB의 원소 2개
		int res = 2;

		// 둘 중 작은 원소부터 시작
		long a = idxN == 0 ? Long.MIN_VALUE : arrN[idxN];
		long b = idxM == 0 ? Long.MIN_VALUE : arrM[idxM];
		long maxElement = Math.max(a, b);

		// 다음 원소 시작
		for (int nextN = idxN+1; nextN <= n; nextN++) {
			if (maxElement < arrN[nextN]) {
				res = Math.max(res, jlis(nextN, idxM) + 1);
			}
		}

		for (int nextM = idxM+1; nextM <= m; nextM++) {
			if (maxElement < arrM[nextM]) {
				res = Math.max(res, jlis(idxN, nextM) + 1);
			}
		}

		D[idxN+1][idxM+1] = res;
		return res;
	}
}
