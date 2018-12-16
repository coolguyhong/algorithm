package search.baekjoon.cuttingtree;

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
	
	private static int N, M;
	private static int[] tree;


	// 백준 알고리즘
	// 나무 자르기(2805번) : 이분 탐색
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		tree = new int[N+1];
		st = new StringTokenizer(br.readLine());
		int a, max = 0;
		for (int i = 1; i <= N; i++) {
			a = Integer.parseInt(st.nextToken());
			tree[i] = a;
			max = Math.max(max, a);
		}
		
		int H = 0;
		int mid;
		int min = 0;
		long length;
		while (min < max) {
			length = 0;
			mid = (min + max) / 2;
			for (int i = 1; i <= N; i++) {
				if (tree[i] > mid) {
					length += (tree[i] - mid);
				}
			}
			
			if (length >= M) {
				H = mid;
				min = mid + 1;
			} else {
				max = mid;
			}
		}
		
		bw.write(H + "\n");
		bw.flush();
		bw.close();
		
	}
}
