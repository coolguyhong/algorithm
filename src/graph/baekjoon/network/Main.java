package graph.baekjoon.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, M;
	private static int[][] G;
	private static int[] D;

	// 최소신장트리, Kruskal 알고리즘
	// 백준: 네트워크 연결(1922)
	public static void main(String[] args) {
		InputReader input = new InputReader(System.in);
		
		N = input.nextInt();
		M = input.nextInt();
		
		D = new int[N+1];
		for (int i = 1; i <= N; i++) {
			D[i] = i;
		}
		
		int a, b, c;
		G = new int[M][3];
		for (int i = 0; i < M; i++) {
			a = input.nextInt();
			b = input.nextInt();
			c = input.nextInt();
			
			G[i][0] = a;
			G[i][1] = b;
			G[i][2] = c;
		}
		
		long ans = asdfasdf();
		System.out.println(ans);
	}

	private static long asdfasdf() {
		Arrays.sort(G, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return Integer.compare(arg0[2], arg1[2]);
			}
		});
		
		int a, b;
		long ans = 0;
		int cnt = 0;
		for (int i = 0; i < M; i++) {
			a = G[i][0];
			b = G[i][1];
			
			if (isUnion(a, b)) {
				continue;
			}
			
			union(a, b);
			ans += G[i][2];
			cnt++;
			
			if (cnt == N-1) {
				break;
			}
		}
		
		return ans;
	}

	private static boolean isUnion(int a, int b) {
		return find(a) == find(b);
	}

	private static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		D[pa] = pb;
		
	}

	private static int find(int a) {
		if (D[a] == a) {
			return a;
		} else {
			return D[a] = find(D[a]);
		}
	}

}

class InputReader {
	public BufferedReader br;
	public StringTokenizer st;
	
	public InputReader(InputStream stream) {
		br = new BufferedReader(new InputStreamReader(stream), 32768);
		st = null;
	}
	
	public String next() {
		while (st == null || !st.hasMoreElements()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return st.nextToken();
	}
	
	public int nextInt() {
		return Integer.parseInt(next());
	}
}
