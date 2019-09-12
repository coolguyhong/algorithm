package graph.algospot.gallery;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int G, H;
    private static List<Integer>[] links;
    private static int[] visited;
    private static int cnt;
    private static final int UNWATCHED = 0;
    private static final int WATCHED = 1;
    private static final int INSTALLED = 2;

    // 알고스팟 문제해결전략
    // https://algospot.com/judge/problem/read/GALLERY
    // 감시 카메라 설치(GALLERY): 그래프
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int testCase = Integer.parseInt(st.nextToken());
        while (testCase-- > 0) {
            st = new StringTokenizer(br.readLine());
            G = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            links = new ArrayList[G];
            visited = new int[G];
            for (int i = 0; i < G; i++) {
                links[i] = new ArrayList<>();
            }

            int a, b;
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());

                links[a].add(b);
                links[b].add(a);
            }

            cnt = 0;
            for (int i = 0; i < G; i++) {
                if (visited[i] == 0 && dfs(i) == UNWATCHED) {
                    cnt++;
                }
            }

            bw.write(cnt + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static int dfs(int n) {
        visited[n]++;
        int[] children = {0, 0, 0};

        for (int c : links[n]) {
            if (visited[c] != 0) {
                continue;
            }

            children[dfs(c)]++;
        }

        if (children[UNWATCHED] != 0) {
            cnt++;
            return INSTALLED;
        }

        if (children[INSTALLED] != 0) {
            return WATCHED;
        }

        return UNWATCHED;
    }
}
