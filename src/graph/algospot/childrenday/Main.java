package graph.algospot.childrenday;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int n, m;
    private static String d;
    private static int[] c;

    // 알고스팟 문제해결능력
    // https://algospot.com/judge/problem/read/CHILDRENDAY
    // 어린이날(그래프): bfs, 수학
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            d = st.nextToken();
            String[] ss = d.split("");

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            c = new int[ss.length];
            for (int i = 0; i < d.length(); i++) {
                c[i] = Integer.parseInt(ss[i]);
            }

            Arrays.sort(c);
            bw.write(bfs() + "\n");
        }

        bw.flush();
        bw.close();

    }

    private static String bfs() {
        int[] parent = new int[2*n];
        Arrays.fill(parent, -1);

        int[] choice = new int[2*n];
        Arrays.fill(choice, -1);

        Queue<Integer> q = new LinkedList<>();
        parent[0] = 0;
        q.add(0);

        int here;
        while (!q.isEmpty()) {
            here = q.poll();

            for (int i = 0; i < c.length; i++) {
                int there = append(here, c[i], n);
                if (parent[there] == -1) {
                    parent[there] = here;
                    choice[there] = c[i];
                    q.add(there);
                }
            }
        }

        if (parent[n+m] == -1) {
            return "IMPOSSIBLE";
        }

        StringBuffer ret = new StringBuffer();
        here = n + m;
        while (parent[here] != here) {
            ret.append(choice[here]);
            here = parent[here];
        }
        return ret.reverse().toString();
    }

    private static int append(int here, int edge, int mod) {
        int there = here * 10 + edge;
        if (there >= mod) {
            return mod + there % mod;
        }
        return there % mod;
    }
}
