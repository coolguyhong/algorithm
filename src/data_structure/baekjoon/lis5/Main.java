package data_structure.baekjoon.lis5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N, idx;
    private static int[] D;
    private static int[] trees;
    private static int[][] A;
    private static int[] prev;
    private static int[] ans;
    private static int[] maxidx;
    private static int ret_idx;

    // 백준 알고리즘
    // 가장 긴 증가하는 부분 수열 5(14003) : indexed tree
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = new int[N+1];
        trees = new int[3*N];
        A = new int[N+1][2];
        prev = new int[N+1];
        ans = new int[N+1];
        maxidx = new int[3*N];

        idx = 1;
        while (idx < N) {
            idx *= 2;
        }
        idx--;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            D[i] = Integer.parseInt(st.nextToken());
            A[i][0] = D[i];
            A[i][1] = i;
        }

        // 값으로 오름차순, 인덱스는 내림차순
        Arrays.sort(A, 1, N+1, new comp());

        // 값이 작은 값 부터 처리
        for (int i = 1; i <= N; i++) {
            // 현재 인덱스보다 앞에 있는 값 중
            // 가장 LIS 값이 큰 값과 인덱스를 구해온다.
            int maxCnt = query(idx + 1, A[i][1] + idx - 1);

            // 현재 인덱스 앞에 연결된 LIS 인덱스 갱신
            prev[A[i][1]] = ret_idx - idx;
            
            // 현재 인덱스까지 사용해서 만들 수 있는 LIS 값 갱신: maxCnt + 1;
            update(A[i][1] + idx, maxCnt + 1);
        }

        bw.write(trees[1] + "\n");

        // 가장 긴 LIS 길이 값을 만드는 마지막 인덱스
        int cur = maxidx[1] - idx;
        for (int i = trees[1]; i >= 1; i--) {
            ans[i] = D[cur];
            cur = prev[cur];
        }

        for (int i = 1; i <= trees[1]; i++) {
            bw.write(ans[i] + " ");
        }
        bw.write("\n");
        bw.flush();
        bw.close();
    }

    private static void update(int idx, int num) {
        int cur = idx;
        while (cur > 0) {
            if (trees[cur] < num) {
                trees[cur] = num; // max tree 값 갱신
                maxidx[cur] = idx; // max 인덱스 정보 갱신
                cur /= 2;
            } else {
                break;
            }
        }
    }

    private static int query(int s, int e) {
        int ret = 0; // 구간의 max값 저장
        ret_idx = -1; // 구간의 max값을 가지는 인덱스 저장

        while (s <= e) {
            if (s % 2 == 1) {
                if (ret < trees[s]) {
                    ret = trees[s];
                    ret_idx = maxidx[s];
                }
            }

            if (e % 2 == 0) {
                if (ret < trees[e]) {
                    ret = trees[e];
                    ret_idx = maxidx[e];
                }
            }

            s = (s + 1) / 2;
            e = (e - 1) / 2;
        }
        return ret;
    }

    private static class comp implements Comparator<int[]> {

        @Override
        public int compare(int[] o1, int[] o2) {
            int compare = Integer.compare(o1[0], o2[0]);
            if (compare == 0) {
                compare = Integer.compare(o2[1], o1[1]);
            }
            return compare;
        }
    }
}
