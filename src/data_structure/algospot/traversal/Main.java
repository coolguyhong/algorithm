package data_structure.algospot.traversal;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N;
    private static List<Integer> pre;
    private static List<Integer> in;

    // 알고스팟
    // 트리 순회 순서 변경(traversal)
    // https://algospot.com/judge/problem/read/TRAVERSAL
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        while (C-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            pre = new ArrayList<>();
            for (int i = 1; i <= N; i++) {
                pre.add(Integer.parseInt(st.nextToken()));
            }

            st = new StringTokenizer(br.readLine());
            in = new ArrayList<>();
            for (int i = 1; i <= N; i++) {
                in.add(Integer.parseInt(st.nextToken()));
            }

            printPostOrder(pre, in);
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    private static void printPostOrder(List<Integer> pre, List<Integer> in) throws IOException {
        if (pre.isEmpty()) {
            return;
        }

        int N = pre.size();
        int root = pre.get(0);
        int l = in.indexOf(root);

        // 왼쪽
        printPostOrder(pre.subList(1, l+1), in.subList(0, l));
        // 오른쪽
        printPostOrder(pre.subList(l+1, N), in.subList(l+1, N));

        // 루트
        bw.write(root + " ");
    }
}