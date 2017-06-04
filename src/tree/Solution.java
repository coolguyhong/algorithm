package tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	private static int T, N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			int ans = N;
			for (int i = 1; i <= M; i++) {
				st = new StringTokenizer(br.readLine());
				int l = Integer.parseInt(st.nextToken(st.nextToken()));
				int r = Integer.parseInt(st.nextToken(st.nextToken()));
				ans = Math.min(ans, r-l+1);
			}
			
			bw.write("#" + testCase + "\n" + ans + "\n");
			
			for (int i = 0; i < N; i++) {
				bw.write((i % ans) + " ");
			}
			
			bw.write("\n");
		}
		bw.flush();
	}

}
