package graph.baekjoon.bit_find_path;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N, K, S, E;
    private static Code[] codes;
    private static int[] visited;

    // 백준 알고리즘
    // https://www.acmicpc.net/problem/2479
    // 경로찾기(2479): 그래프
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new int[N+1];
        codes = new Code[N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            codes[i] = new Code(i, st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        bfs();

        if (codes[E].path.size() == 0) {
            bw.write("-1");
        } else {
            for (int c : codes[E].path) {
                bw.write(c + " ");
            }
        }
        bw.flush();
        bw.close();

    }

    private static void bfs() {
        Queue<Code> q = new LinkedList<>();
        q.add(codes[S]);

        visited[S]++;
        Code c;
        while (!q.isEmpty()) {
            c = q.poll();
            if (c.no == E) {
                c.addCode(E);
                break;
            }

            for (int i = 1; i <= N; i++) {
                if (c.no == i) {
                    continue;
                }

                if (visited[i] != 0) {
                    continue;
                }

                if (hammingLength(c.code, codes[i].code) == 1) {
                    visited[i]++;
                    codes[i].path.addAll(c.path);
                    codes[i].addCode(c.no);
                    q.add(codes[i]);
                }
            }
        }
    }

    private static int hammingLength(String a, String b) {
        int cnt = 0;
        for (int i = 0; i < K; i++) {
            if (a.charAt(i) == b.charAt(i)) {
                continue;
            }
            cnt++;
        }
        return cnt;
    }
}

class Code {
    int no;
    String code;
    List<Integer> path = new ArrayList<>();

    public Code(int no, String code) {
        this.no = no;
        this.code = code;
    }

    public void addCode(int n) {
        path.add(n);
    }
}
