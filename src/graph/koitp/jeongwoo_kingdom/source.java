package graph.koitp.jeongwoo_kingdom;

import java.util.*;

public class source {

    static int color[];
    static class Edge {
        int vA, vB, weight;
        public Edge(int vA, int vB, int weight) {
            this.vA = vA;
            this.vB = vB;
            this.weight = weight;
        }
    }
    static class City{
        int x,y,z;
        int index;
        public City(int x,int y,int z,int index){
            this.x = x;
            this.y = y;
            this.z = z;
            this.index = index;
        }
        static int abs(int a){
            if(a<0)return -a;
            return a;
        }
        static int min2(int a,int b){
            if(a<b)return a;
            return b;
        }
        static int min(int a,int b,int c){
            return min2(a,min2(b,c));
        }
        int cost(City c){
            return min(abs(x-c.x),abs(y-c.y),abs(z-c.z));
        }
    }
    static int f(int x) {
        if (color[x] == x) return x;
        color[x] = f(color[x]);
        return color[x];
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(); //number of Vertex
        color = new int[n];
        List<Edge> E = new ArrayList<Edge>();
        List<City> V = new ArrayList<City>();
        for (int i = 0; i < n; i++) {
            int x = scan.nextInt();
            int y = scan.nextInt();
            int z = scan.nextInt();
            V.add(new City(x, y, z, i));
        }
        Collections.sort(V, new CompareX());
        for(int i=0;i<n-1;i++){
            E.add(new Edge(V.get(i).index,V.get(i+1).index,V.get(i).cost(V.get(i+1))));
        }
        Collections.sort(V, new CompareY());
        for(int i=0;i<n-1;i++){
            E.add(new Edge(V.get(i).index,V.get(i+1).index,V.get(i).cost(V.get(i+1))));
        }
        Collections.sort(V, new CompareZ());
        for(int i=0;i<n-1;i++){
            E.add(new Edge(V.get(i).index,V.get(i+1).index,V.get(i).cost(V.get(i+1))));
        }
        Collections.sort(E, new CompareE());
        for (int i = 0; i < n; i++) color[i] = i;
        long res = 0;
        for (Edge edge : E) {
            int p = edge.vA, q = edge.vB;
            int set_p = f(p), set_q = f(q);
            if (set_p == set_q) continue;
            color[set_p] = set_q;
            res += edge.weight;
        }
        System.out.println(res);
    }
    static class CompareE implements Comparator<Edge> {
        public int compare(Edge e1, Edge e2) {
            return e1.weight-e2.weight;
        }
    }
    static class CompareX implements Comparator<City> {
        public int compare(City c1, City c2) {
            return c1.x-c2.x;
        }
    }
    static class CompareY implements Comparator<City> {
        public int compare(City c1, City c2) {
            return c1.y-c2.y;
        }
    }
    static class CompareZ implements Comparator<City> {
        public int compare(City c1, City c2) {
            return c1.z-c2.z;
        }
    }
}