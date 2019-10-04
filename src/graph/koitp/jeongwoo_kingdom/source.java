package graph.koitp.jeongwoo_kingdom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class source {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int N;
    private static int[] D;

    // sw 문제풀이반
    // https://koitp.org/problem/SDS_3_5/read/
    // 정우왕국, 크루스칼
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        D = new int[N+1];
        for (int i = 0; i <= N; i++) {
            D[i] = i;
        }

        List<City> cities = new ArrayList<>();
        int x, y, z;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            z = Integer.parseInt(st.nextToken());

            cities.add(new City(x, y, z, i));
        }

        List<Link> links = new ArrayList<>();
        // x로 오름차순 정렬
        Collections.sort(cities, (a, b) -> Integer.compare(a.x, b.x));
        for (int i = 0; i < N-1; i++) {
            links.add(new Link(cities.get(i).idx, cities.get(i+1).idx, cities.get(i).cost(cities.get(i+1))));
        }

        // y로 오름차순 정렬
        Collections.sort(cities, (a, b) -> Integer.compare(a.y, b.y));
        for (int i = 0; i < N-1; i++) {
            links.add(new Link(cities.get(i).idx, cities.get(i+1).idx, cities.get(i).cost(cities.get(i+1))));
        }

        // z로 오름차순 정렬
        Collections.sort(cities, (a, b) -> Integer.compare(a.z, b.z));
        for (int i = 0; i < N-1; i++) {
            links.add(new Link(cities.get(i).idx, cities.get(i+1).idx, cities.get(i).cost(cities.get(i+1))));
        }
        // 이렇게 함으로써 links에 비용이 많이드는 간선이 들어가지 않음

        Collections.sort(links, (a, b) -> Integer.compare(a.c, b.c));
        long ans = 0;
        int cnt = 0;
        for (Link link : links) {
            int ps = find(link.s);
            int pe = find(link.e);
            if (ps == pe) {
                continue;
            }

            if (cnt == N-1) {
                break;
            }

            cnt++;
            ans += link.c;
            D[pe] = ps;
        }

        bw.write(ans + "\n");
        bw.close();
    }

    private static int find(int a) {
        if (D[a] == a) {
            return a;
        } else {
            return D[a] = find(D[a]);
        }
    }
}

class City {
    int x, y, z, idx;

    City(int x, int y, int z, int idx) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.idx = idx;
    }

    int cost(City c) {
        return Math.min(Math.abs(this.x - c.x), Math.min(Math.abs(this.y - c.y), Math.abs(this.z - c.z)));
    }
}

class Link {
    int s, e, c;

    Link(int s, int e, int c) {
        this.s = s;
        this.e = e;
        this.c = c;
    }
}