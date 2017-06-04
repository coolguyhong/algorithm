package key;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution {
	private static int T, N, H, R;
	private static int[] S;
	private static TreeSet<Pair> vis;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			S = new int[N+1];
			vis = new TreeSet<>();
			S[1] = H;
			
			for (int i = 1; i <= R; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (a > b) {
					int c = a;
					a = b;
					b = c;
				}
				
				Pair p = new Pair(a, b);
				if (vis.contains(p)) {
					continue;
				}
				S[a+1]--;
				S[b]++;
				vis.add(p);
			}
			
			for (int i = 1; i <= N; i++) {
				S[i] += S[i-1];
			}
			
			bw.write("#" + testCase);
			
			for (int i = 1; i <= N; i++) {
				bw.write(" " + S[i]);
			}
			
			bw.write("\n");
		}
		bw.flush();
	}

}
