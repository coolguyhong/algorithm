package graph.baekjoon.breakpoint;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;

	private static int V, E;
	private static int order;
	private static int[] visitedOrder;
	private static boolean[] isCutV;
	private static List<Integer>[] links;

	// 백준 알고리즘
	// https://www.acmicpc.net/problem/11266
	// 단절점(11266): dfs 활용
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		visitedOrder = new int[V+1];
		isCutV = new boolean[V+1];
		links = new ArrayList[V+1];

		for (int i = 1; i <= V; i++) {
			links[i] = new ArrayList<>();
		}

		int a, b;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			links[a].add(b);
			links[b].add(a);
		}

		order = 0;
		for (int i = 1; i <= V; i++) {
			if (visitedOrder[i] != 0) {
				continue;
			}
			dfs(i, -1);
		}

		int cnt = 0;
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i <= V; i++) {
			if (isCutV[i]) {
				cnt++;
				sb.append(i + " ");
			}
		}

		bw.write(cnt + "\n");
		bw.write(sb + "\n");
		bw.flush();
		bw.close();
	}

	private static int dfs(int n, int p) {
		if (visitedOrder[n] != 0) {
			return visitedOrder[n];
		}

		order++;
		visitedOrder[n] = order;
		int minLow = visitedOrder[n];

		int child = 0;
		for (int c : links[n]) {
			if (c == p) {
				continue;
			}

			if (visitedOrder[c] != 0) {
				minLow = min(minLow, visitedOrder[c]);
			} else {
				child++;
				int low = dfs(c, n);

				if (p != -1 && visitedOrder[n] <= low) {
					isCutV[n] = true;
				}
				minLow = min(minLow, low);
			}
		}

		if (p == -1 && child > 1) {
			isCutV[n] = true;
		}

		return minLow;
	}

	private static int min(int a, int b) {
		return (a < b) ? a : b;
	}
}
