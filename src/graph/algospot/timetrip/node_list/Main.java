package graph.algospot.timetrip.node_list;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int V, W;
    private static int[] min_d, max_d;
    private static final int INF = 1000001;
    private static List<int[]>[] min_links, max_links;

    // 알고스팟 문제 해결 능력
    // https://algospot.com/judge/problem/read/TIMETRIP
    // 시간여행(TIMETRIP): 최단거리, 벨만포드 알고리즘
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            min_d = new int[V];
            Arrays.fill(min_d, INF);
            max_d = new int[V];
            Arrays.fill(max_d, INF);

            min_links = new ArrayList[V];
            max_links = new ArrayList[V];

            for (int i = 0; i < V; i++) {
                min_links[i] = new ArrayList<>();
                max_links[i] = new ArrayList<>();
            }

            int a, b, d;
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                d = Integer.parseInt(st.nextToken());

                min_links[a].add(new int[]{b, d});
                max_links[a].add(new int[]{b, d * -1});
            }
            
            min_d[0] = 0;
            boolean updated = false;
            for (int i = 0; i < V; i++) {
                updated = false;
                for (int here = 0; here < V; here++) {
                    for (int j = 0; j < min_links[here].size(); j++) {
                        if (min_d[min_links[here].get(j)[0]] > min_d[here] + min_links[here].get(j)[1]) {
                            min_d[min_links[here].get(j)[0]] = min_d[here] + min_links[here].get(j)[1];
                            updated = true;
                        }
                    }
                }

                if (!updated) {
                    break;
                }
            }

            if (min_d[1] == INF) {
                bw.write("UNREACHABLE\n");
                continue;
            }

            if (updated) {
                bw.write("INFINITY ");
            } else {
                bw.write(min_d[1] + " ");
            }

            max_d[0] = 0;
            for (int i = 0; i < V; i++) {
                updated = false;
                for (int here = 0; here < V; here++) {
                    for (int j = 0; j < max_links[here].size(); j++) {
                        if (max_d[max_links[here].get(j)[0]] > max_d[here] + max_links[here].get(j)[1]) {
                            max_d[max_links[here].get(j)[0]] = max_d[here] + max_links[here].get(j)[1];
                            updated = true;
                        }
                    }
                }

                if (!updated) {
                    break;
                }
            }

            if (max_d[1] == INF) {
                bw.write("UNREACHABLE\n");
                continue;
            }

            if (updated) {
                bw.write("INFINITY");
            } else {
                bw.write((max_d[1] * -1));
            }
            bw.newLine();

        }
        bw.close();
    }
}
