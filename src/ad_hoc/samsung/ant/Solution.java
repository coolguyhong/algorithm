package ad_hoc.samsung.ant;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    private static int N, K, L;
    private static Ant[] A;
    private static Falling[] F;

    // 프로교육(개발실)
    // adhoc
    // [교육P-0002] 개미
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int testCase = Integer.parseInt(st.nextToken());
        for (int TC = 1; TC <= testCase; TC++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            A = new Ant[N+1];
            F = new Falling[N+1];

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                char dir = st.nextToken().charAt(0);

                A[i] = new Ant(i, x, dir);

                if (dir == 'L') {
                    F[i] = new Falling(x, dir);
                } else {
                    F[i] = new Falling(L-x, dir);
                }
            }

            // 거리에 대해서 오름차순으로 정렬
            Arrays.sort(A, 1, N+1, (a, b) -> Integer.compare(a.x, b.x));

            // 시간에 대해 정렬, 시간이 동일 할 경우 방향에 대해 정렬
            Arrays.sort(F, 1, N+1, (a, b) -> {
                int c = Integer.compare(a.time, b.time);
                if (c == 0) {
                    c = Character.compare(a.dir, b.dir);
                }
                return c;
            });

            int l = 1;
            int r = N;
            for (int i = 1; i < K; i++) {
                if (F[i].dir == 'L') {
                    l++;
                } else {
                    r--;
                }
            }

            int num;
            if (F[K].dir == 'L') {
                num = A[l].no;
            } else {
                num = A[r].no;
            }

            bw.write("#" + TC + " " + num + " " + F[K].time + "\n");
        }

        bw.flush();
        bw.close();
    }
}

class Ant {
    int no;
    int x;
    char dir;

    public Ant(int no, int x, char dir) {
        this.no = no;
        this.x = x;
        this.dir = dir;
    }
}

class Falling {
    int time;
    char dir;

    public Falling(int time, char dir) {
        this.time = time;
        this.dir = dir;
    }
}
