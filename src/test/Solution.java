package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    //
    //
    //
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int timestampCount = Integer.parseInt(br.readLine());
        List<Integer> timestamp = new ArrayList<>();
        for (int i = 0; i < timestampCount; i++) {
            timestamp.add(Integer.parseInt(br.readLine()));
        }

        int topCount = Integer.parseInt(br.readLine());
        List<Integer> top = new ArrayList<>();
        for (int i = 0; i < topCount; i++) {
            top.add(Integer.parseInt(br.readLine()));
        }

        int result = Result.requestsServed(timestamp, top);

        bw.write( result + "\n");
        bw.close();
    }
}

class Result {
    public static int requestsServed(List<Integer> timestamp, List<Integer> top) {
        Collections.sort(timestamp);
        Collections.sort(top, (a, b) -> Integer.compare(b, a));
        int ans = 0;
        for (int t : top) {
            int tmp = 0;
            List<Integer> deleteIdx = new ArrayList<>();
            for (int i = timestamp.size() - 1; i >= 0; i--) {
                if (t >= timestamp.get(i)) {
                    tmp++;
                    deleteIdx.add(i);
                }
                if (tmp == 5) {
                    break;
                }
            }
            ans += tmp;
            for (int delete : deleteIdx) {
                timestamp.remove(delete);
            }
        }
        return ans;
    }
}