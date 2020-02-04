package dp.algospot.lis;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int N;
	private static int[] arr, D;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int testCase = Integer.parseInt(br.readLine());
		while (testCase-- > 0) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N+1];
			D = new int[N+2];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			bw.write((lis3(0) - 1) + "\n");
		}
		bw.close();
	}

	private static int lis3(int s) {
		if (D[s+1] != 0) {
			return D[s+1];
		}

		int ret = 1;
		for (int next = s+1; next <= N; next++) {
			if (arr[s] < arr[next]) {
				ret = Math.max(ret, lis3(next) + 1);
			}
		}
		D[s+1] = ret;
		return ret;
	}
}