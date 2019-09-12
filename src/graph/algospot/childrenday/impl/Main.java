package graph.algospot.childrenday.impl;

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

    private static String d;
    private static int N, M;
    private static int[] c;

    // 알고스팟
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            d = st.nextToken();
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            c = new int[d.length()];
            for (int i = 0; i < c.length; i++) {
                c[i] = Character.getNumericValue(d.charAt(i));
            }

            Arrays.sort(c);
            bw.write(bfs() + "\n");
        }
        bw.flush();
        bw.close();

    }

    private static String bfs() {
        int[] parents = new int[2*N];
        Arrays.fill(parents, -1);

        int[] choice = new int[2*N];
        Arrays.fill(choice, -1);

        Queue<Integer> q = new LinkedList<>();
        parents[0] = 0;
        q.add(0);

        int here;
        while (!q.isEmpty()) {
            here = q.poll();

            for (int i = 0; i < c.length; i++) {
                int there = append(here, c[i], N);
                if (parents[there] == -1) {
                    parents[there] = here;
                    choice[there] = c[i];
                    q.add(there);
                }
            }
        }

        here = N+M;
        if (parents[here] == -1) {
            return "IMPOSSIBLE";
        }

        StringBuffer sb = new StringBuffer();
        while (parents[here] != here) {
            sb.append(choice[here]);
            here = parents[here];
        }

        return sb.reverse().toString();
    }

    private static int append(int here, int edge, int mod) {
        int there = here * 10 + edge;
        if (there >= mod) {
            return mod + there % mod;
        }
        return there % mod;
    }
}
