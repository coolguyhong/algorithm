package graph.samsung.sand_castle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;
	
	private static int N, M;
	private static List<Integer>[] G;
	private static int[] depth;
	private static int[] parents;
	private static int[] indegrees;

	// 사내 알고리즘
    // 1cm의 자존심
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			G = new ArrayList[N+1];
			depth = new int[N+1];
			parents = new int[N+1];
			indegrees = new int[N+1];
			for (int i = 1; i <= N; i++) {
				G[i] = new ArrayList<>();
			}
			
			int a, b;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				G[a].add(b);
				indegrees[b]++;
			}
			
			for (int i = 1; i <= N; i++) {
				if (indegrees[i] == 0) {
					dfs(i, 0);
				}
			}
			
			bw.write("#" + testCase + " ");
			for (int i = 1; i <= N; i++) {
				bw.write(depth[i] + 1 + " ");
			}
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		
	}

	private static int dfs(int v, int p) {
        if (p != 0 && depth[v] > 0) {
            return depth[v];
        }
        
        parents[v] = p;
        for (int c : G[v]) {
            if (c == p) {
                continue;
            }
            
            int dep = dfs(c, v);
            depth[v] = Math.max(depth[v], dep + 1);
            depth[p] = Math.max(depth[p], depth[v] + 1);
        }
        
        return depth[v];
    }
}
