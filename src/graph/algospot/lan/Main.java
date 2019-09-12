package graph.algospot.lan;

import java.io.*;
import java.util.*;

public class Main {

    private static int N, M;
    private static int[][] node;
    private static int[] D;
    private static double[][] edge; // 한점을 기준으로 다른 점까지의 거리를 저장한 것

    // 알고스팟 문제 해결 능력
    // https://algospot.com/judge/problem/read/LAN
    // 근거리 네트워크(LAN): 그래프, union - find, 크루스칼 알고리즘(시간초과)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        while (C-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            D = new int[N];
            for (int i = 0; i < N; i++) {
                D[i] = i;
            }

            node = new int[N][2];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                node[i][0] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                node[i][1] = Integer.parseInt(st.nextToken());
            }

            int a, b;
            int cnt = 0;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());

                union(a, b);
                cnt++;
            }

            List<double[]> links = new ArrayList<>();
            for (int i = 0; i < N * N; i++) {
                a = i / N; // 이 점을 기준으로
                b = i % N; // 다른 점들
                if (a == b) {
                    continue;
                }
                
                if (find(a) == find(b)) {
                    continue;
                }
                
                double d = Math.sqrt( // 거리...
                        Math.abs(node[a][0] - node[b][0])
                        * Math.abs(node[a][0] - node[b][0])
                        + Math.abs(node[a][1] - node[b][1])
                        * Math.abs(node[a][1] - node[b][1]));
                links.add(new double[]{a, b, d});
            }

            Collections.sort(links, (double[] a1, double[] b1) -> Double.compare(a1[2], b1[2]));

            double sum = 0;

            for (int i = 0; i < links.size(); i++) {
                double[] link = links.get(i);
                if (find((int) link[0]) != find((int) link[1])) {
                    union((int) link[0], (int) link[1]);
                    sum += link[2];
                    cnt++;
                }

//                if (cnt == N-1) {
//                    break;
//                }
            }

            bw.write(sum + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa != pb) {
            D[pb] = pa;
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
