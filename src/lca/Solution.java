package lca;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {

    // 최저 공통 조상(LCA)
    public static void main(String[] args) throws IOException {
    }
}

class InputReader  {
    private BufferedReader br;
    private StringTokenizer st;

    public InputReader(InputStream stream) {
        br = new BufferedReader(new InputStreamReader(stream), 32768);
        st = null;
    }

    public String next() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }
}
