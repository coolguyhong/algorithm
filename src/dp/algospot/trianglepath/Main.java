package dp.algospot.trianglepath;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 알고스팟
// https://algospot.com/judge/problem/read/TRIANGLEPATH
// 알고스팟(dp) 최적화, triangle path
public class Main {

	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int n;
	private static int[][] triangle, D;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int testCase = Integer.parseInt(br.readLine());
		while (testCase-- > 0) {
			n = Integer.parseInt(br.readLine());
			triangle = new int[n+1][n+1];
			D = new int[n+1][n+1];
			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= i; j++) {
					triangle[i][j] = Integer.parseInt(st.nextToken());
					D[i][j] = -1;
				}
			}

			bw.write(path(1, 1) + "\n");
		}
		bw.close();
	}
	
	private static int path(int x, int y) {
		//삼각형 제일 하단으로 내려온 경우
		if (x == n) {
			D[x][y] = triangle[x][y];
			return D[x][y];
		}

		if (D[x][y] != -1) {
			return D[x][y];
		}
		
		return D[x][y] = Math.max(path(x+1, y), path(x+1, y+1)) + triangle[x][y];
	}
}
