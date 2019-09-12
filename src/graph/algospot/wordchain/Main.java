package graph.algospot.wordchain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N;
    private static int[] indegree, outdegree;
    private static List<String>[] links;
    private static int[][] path;
    private static String[] str;


    // algospot
    // 단어 제한 끝말잇기(WORDCHAIN, 난이도: 하)
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            str = new String[N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                str[i] = st.nextToken();
            }

            path = new int[26][26];
            indegree = new int[26];
            outdegree = new int[26];
            links = new ArrayList[26];
            for (int i = 0; i < 26; i++) {
                links[i] = new ArrayList<>();
            }

            makeGraph();
            if (!checkEuler()) {
                bw.write("IMPOSSIBLE" + "\n");
            } else {
                List<Integer> result = getEulerTrailOrCircuit();
                Collections.reverse(result);

                String ret = "";
                for (int i = 1; i < result.size(); i++) {
                    int a = result.get(i-1);
                    int b = result.get(i);

                    if (ret.length() > 0) {
                        ret += " ";
                    }

                    String temp = "";
                    for (int j = 0; j < links[a].size(); j++) {
                        temp = links[a].get(j);
                        if (temp.charAt(temp.length() - 1) - 'a' == b) {
                            ret += temp;
                            links[a].remove(j);
                            break;
                        }
                    }
                }

                bw.write(ret + "\n");
            }
        }
        bw.flush();
        bw.close();
    }

    private static boolean checkEuler() {
        int plus1 = 0;
        int minus1 = 0;
        for (int i = 0; i < 26; i++) {
            int delta = outdegree[i] - indegree[i];

            if (delta < -1 || 1 < delta) {
                return false;
            } else if (delta == 1) {
                plus1++;
            } else if (delta == -1) {
                minus1++;
            }
        }

        return (plus1 == 1 && minus1 == 1) || (plus1 == 0 && minus1 == 0);
    }

    private static List<Integer> getEulerTrailOrCircuit() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (outdegree[i] == indegree[i] + 1) {
                getEulerCircuit(i, result);
                return result;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (outdegree[i] > 0) {
                getEulerCircuit(i, result);
                return result;
            }
        }

        return result;
    }

    // dfs
    private static void getEulerCircuit(int n, List<Integer> result) {
        for (int e = 0; e < 26; e++) {
            while (path[n][e] > 0) {
                path[n][e]--;
                getEulerCircuit(e, result);
            }
        }

        result.add(n);
    }


    private static void makeGraph() {
        for (int i = 0; i < N; i++) {
            int s = str[i].charAt(0) - 'a';
            int e = str[i].charAt(str[i].length() - 1) - 'a';
            links[s].add(str[i]);
            path[s][e]++;
            outdegree[s]++;
            indegree[e]++;
        }
    }
}