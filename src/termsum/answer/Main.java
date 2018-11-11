package termsum.answer;

import java.io.*;
import java.util.*;
public class Main {
    static int N,M,K;
    //100������ ū 2�� ������ : 1048576 -> �ִ� �� 210������ �迭�� �ʿ�, �뷫 �������� 4�������� ���
    static long tree[] = new long[1000000*4];

    static int init() // Ʈ�� �ʱ�ȭ
    {
        for(int i = 0 ; i < 4*N ; i++) tree[i] = 0;

        // ó������ n���� ū 2�� ������ ���ϱ�
        int ret = 1;
        while(ret < N ) ret *= 2;

        ret--;// 1 base �Է�ó���� ���� 1�� �̸� ���ֱ�

        return ret;
    }

    // ���� �Լ�
    static void update(int node, long delta)
    {
        int cur = node;
        while(cur > 0)
        {
            tree[cur] += delta; // Ʈ���� �����͸� ����
            cur /= 2; // �θ� ���� �̵�
        }

    }

    // ���� ���� �Լ�
    static long sum(int start, int end)
    {
        long ret = 0;
        while(start <= end)
        {
            if(start%2 == 1) ret += tree[start];
            if(end%2 == 0) ret += tree[end];
            start = (start+1)/2;
            end = (end-1)/2;
        }
        return ret;
    }

    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // Ʈ�� �ʱ�ȭ �� �������� ù �ε��� ���ϱ�
        int Nidx = init();

        for(int i = 1 ; i <= N ; i++) 
        {
            // �ε��� Ʈ���� ���� ��忡 ������ �Է�
            tree[Nidx+i] = (long)Integer.parseInt(br.readLine());
        }

        // Ʈ�� ���� �ϱ�
        for(int i = Nidx ; i > 0 ; i--) tree[i] = tree[i*2] + tree[i*2+1];

        for(int i = 0 ; i < M+K ; i++)
        {
            int a,b,c;
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            if(a == 1)
            {
                // ������ ���� �ϱ�
                update(Nidx+b,(long)c-tree[Nidx+b]);
            }
            else
            {
                // ������ �� ���ϱ�
                bw.write(sum(Nidx+b,Nidx+c)+"\n");
            }
        }
        bw.flush();
    }
}
