package minmax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	
	private static BufferedWriter bw;
	private static BufferedReader br;
	private static StringTokenizer st;
	
	private static int N, Q;
	private static int[] maxTrees;
	private static int[] minTrees;
	private static int idx;
	private static int minValue, maxValue;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			
			idx = 1;
			while (idx < N) {
				idx *= 2;
			}
			idx--;
			
			maxTrees = new int[4*N];
			minTrees = new int[4*N];
			st = new StringTokenizer(br.readLine());
			int p;
			for (int i = 1; i <= N; i++) {
				p = Integer.parseInt(st.nextToken());
				maxTrees[idx+i] = p;
				minTrees[idx+i] = p;
			}
			
			makeTree();
			
			int q, a, b;
			minValue = 0;
			maxValue = 0;
			for (int i = 1; i <= Q; i++) {
				st = new StringTokenizer(br.readLine());
				q = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				if (q == 0) {
					long[] results = getMinMax(a+idx, b+idx);
					minValue += results[1];
					maxValue += results[0];
				} else {
					updateTree(a+idx, b);
				}
			}
			
			bw.write("#" + testCase + " " + maxValue + " " + minValue + "\n");
			bw.flush();
		}
		bw.close();
	}

	private static void updateTree(int i, int b) {
		maxTrees[i] = b;
		minTrees[i] = b;
		while (i > 0) {
			i /= 2;
			maxTrees[i] = Math.max(maxTrees[2*i], maxTrees[2*i + 1]);
			minTrees[i] = Math.min(minTrees[2*i], minTrees[2*i + 1]);
		}
	}

	private static long[] getMinMax(int s, int e) {
		long max = 0;
		long min = 10000;
		while (s <= e) {
			if (s % 2 == 1) {
				max = Math.max(max, maxTrees[s]);
				min = Math.min(min, minTrees[s]);
			}
			
			if (e % 2 == 0) {
				max = Math.max(max, maxTrees[e]);
				min = Math.min(min, minTrees[e]);
			}
			
			s = (s + 1) / 2;
			e = (e - 1) / 2;
		}
		
		return new long[] {max, min};
	}

	private static void makeTree() {
		for (int i = idx; i >= 1; i--) {
			maxTrees[i] = Math.max(maxTrees[2*i], maxTrees[2*i + 1]);
			minTrees[i] = Math.min(minTrees[2*i], minTrees[2*i + 1]);
		}
	}

}
