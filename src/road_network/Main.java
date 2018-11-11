package road_network;

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
	
	private static int N, K, D;
	private static List<Integer>[] G;
	private static int[] depth;
	private static int[][] parents;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		depth = new int[N+1];
		G = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			G[i] = new ArrayList<>();
		}
		
		int n = N;
		D = 0;
		while (n > 0) {
			D++;
			n /= 2;
		}
		
		parents = new int[D][N+1];
	}

}
