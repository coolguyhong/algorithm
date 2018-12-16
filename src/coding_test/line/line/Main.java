package coding_test.line.line;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, M;
	private static Node[] nodes;

	// 위상정렬
	// 백준 : 줄 세우기(2252)
	public static void main(String[] args) {
		InputReader input = new InputReader(System.in);
		
		N = input.nextInt();
		M = input.nextInt();
		
		nodes = new Node[N+1];
		for (int i = 1; i <= N; i++) {
			nodes[i] = new Node(i);
		}
		
		int a, b;
		for (int i = 0; i < M; i++) {
			a = input.nextInt();
			b = input.nextInt();
			
			nodes[a].addLink(new Link(nodes[b]));
		}
		
		List<Integer> results = topological();
		results.forEach(e -> System.out.print(e + " "));
	}

	private static List<Integer> topological() {
		Queue<Node> q = new LinkedList<>();
		List<Integer> result = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			Node n = nodes[i];
			if (n.inDegree == 0) {
				q.add(nodes[i]);
				result.add(i);
			}
		}
		
		Node node;
		while (!q.isEmpty()) {
			node = q.poll();
			for (Link link : node.links) {
				link.target.inDegree--;
				if (link.target.inDegree == 0) {
					q.add(link.target);
					result.add(link.target.no);
				}
			}
		}
		
		return result;
	}
}

class Node {
	int no;
	int inDegree;
	List<Link> links = new ArrayList<>();
	
	public Node(int no) {
		super();
		this.no = no;
		this.inDegree = 0;
	}
	
	public void addLink(Link link) {
		links.add(link);
		link.target.inDegree++;
	}
}

class Link {
	Node target;

	public Link(Node target) {
		super();
		this.target = target;
	}
}

class InputReader {
	public BufferedReader br;
	public StringTokenizer st;
	
	public InputReader(InputStream stream) {
		br = new BufferedReader(new InputStreamReader(stream), 32768);
		st = null;
	}
	
	public String next() {
		while (st == null || !st.hasMoreElements()) {
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
