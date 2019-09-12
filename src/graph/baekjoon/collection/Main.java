package graph.baekjoon.collection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N, M;
	private static int[] D = new int[1000001];

	// union find
	// ����: ������ ǥ��(1717)
	public static void main(String[] args) {
		InputReader input = new InputReader(System.in);
		
		N = input.nextInt();
		for (int i = 1; i <= N; i++) {
			D[i] = i;
		}
		
		M = input.nextInt();
		int q, a, b;
		for (int i = 0; i < M; i++) {
			q = input.nextInt();
			a = input.nextInt();
			b = input.nextInt();
			
			if (q == 0) {
				union(a, b);
			} else {
				if (isUnion(a, b)) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}
		}
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
