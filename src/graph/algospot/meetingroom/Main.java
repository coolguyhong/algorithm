package graph.algospot.meetingroom;

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

    private static int N;
    private static List<Integer>[] links;
    private static Meeting[] meetings;

    // 알고스팟 문제 해결 전략
    // https://algospot.com/judge/problem/read/MEETINGROOM
    // 회의실배정(MEETINGROOM): 그래프, 2-sat, scc(강결합 컴포넌트)
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int testCase = Integer.parseInt(st.nextToken());
        while (testCase-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            meetings = new Meeting[2*N];
            links = new ArrayList[4*N];

            for (int i = 0; i < 2*N; i++) {
                links[i] = new ArrayList<>();
            }

            int a, b, c, d;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());
                d = Integer.parseInt(st.nextToken());

                meetings[2*i] = new Meeting(a, b);
                meetings[2*i + 1] = new Meeting(c, d);
            }

            makeGraph();


        }
    }

    private static void makeGraph() {
        for (int i = 0; i < meetings.length; i += 2) {
            int j = i + 1;
            links[i * 2 + 1].add(j * 2);
            links[j * 2 + 1].add(i * 2);
        }

        for (int i = 0; i < meetings.length; i++) {
            for (int j = 0; j < i; j++) {
                if (!disjoint(meetings[i], meetings[j])) {
                    links[i * 2].add(j * 2 + 1);
                    links[j * 2].add(i * 2 + 1);
                }
            }
        }
    }

    private static boolean disjoint(Meeting m1, Meeting m2) {
        return m1.e <= m2.s || m2.e <= m1.s;
    }
}

class Meeting {
    int s;
    int e;

    public Meeting(int s, int e) {
        this.s = s;
        this.e = e;
    }
}
