package graph.baekjoon.friend_network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int F;
    private static int[] D, G, level;
    private static StringBuilder sb;

    // 백준 알고리즘
    // https://www.acmicpc.net/problem/4195
    // 친구 네트워크, 최소 스패닝 트리, 유니언 파인드
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        while (T-- > 0) {
            D = new int[200001];
            G = new int[200001];
            level = new int[200001];

            for (int i = 1; i <= 200000; i++) {
                D[i] = i;
                G[i] = 1;
            }

            F = Integer.parseInt(br.readLine());
            int idx = 1;
            Map<String, Integer> hm = new HashMap<>();
            for (int i = 0; i < F; i++) {
                st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();

                if (!hm.containsKey(a)) {
                    hm.put(a, idx++);
                }
                if (!hm.containsKey(b)) {
                    hm.put(b, idx++);
                }

                int aIdx = hm.get(a);
                int bIdx = hm.get(b);

                union(aIdx, bIdx);
            }
        }
        bw.write(sb.toString());
        bw.close();
    }

    private static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) {
            sb.append(G[pa] + "\n");
            return;
        }

        if (level[pa] > level[pb]) {
            int temp = pa;
            pa = pb;
            pb = temp;
        }

        D[pa] = pb;
        G[pb] += G[pa];
        sb.append(G[pb] + "\n");

        if (level[pa] == level[pb]) {
            level[pb]++;
        }
    }

    private static int find(int a) {
        if (D[a] == a) {
            return a;
        } else {
            return D[a] = find(D[a]);
        }
    }
}
