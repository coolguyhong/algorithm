package graph.algospot.sortgame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N;
    private static Map<ArrayList<Integer>, Integer> discovered;

    // 알고스팟 문제 해결 전략
    // https://algospot.com/judge/problem/read/SORTGAME
    // Sorting Game(SORTGAME): 그래프
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            arr.add(i);
        }

        preClac(arr);

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            Node[] tmp = new Node[N];
            int val;
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                val = Integer.parseInt(st.nextToken());
                tmp[i] = new Node(i, val);
            }

            Arrays.sort(tmp);

            arr = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                arr.add(tmp[i].idx);
            }

            for (int i = N; i < 8; i++) {
                arr.add(i);
            }

            bw.write(discovered.get(arr) + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void preClac(ArrayList<Integer> arr) {
        Queue<ArrayList<Integer>> q = new ArrayDeque<>();
        discovered = new HashMap<>();

        q.add(arr);
        discovered.put(arr, 0);

        ArrayList<Integer> here;
        while (!q.isEmpty()) {
            here = q.poll();
            int level = discovered.get(here);

            for (int i = 0; i < here.size() - 1; i++) {
                for (int j = i; j < here.size(); j++) {
                    ArrayList<Integer> temp = reverse(here, i, j);
                    if (!discovered.containsKey(temp)) {
                        discovered.put(temp, level + 1);
                        q.add(temp);
                    }
                }
            }
        }
    }

    private static ArrayList<Integer> reverse(ArrayList<Integer> arr, int from, int to) {
        ArrayList<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < from; i++) {
            tmp.add(arr.get(i));
        }

        for (int i = to; i >= from; i--) {
            tmp.add(arr.get(i));
        }

        for (int i = to + 1; i < arr.size(); i++) {
            tmp.add(arr.get(i));
        }

        return tmp;
    }
}

class Node implements Comparable<Node> {
    int idx;
    int val;

    public Node(int idx, int val) {
        this.idx = idx;
        this.val = val;
    }

    @Override
    public int compareTo(Node o) {
        return this.val - o.val;
    }
}
