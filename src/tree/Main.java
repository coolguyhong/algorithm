package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N;
	private static Node[] nodes = new Node[26];
	
	public static void main(String[] args) {
		InputReader input = new InputReader(System.in);
		
		N = input.nextInt();
		for (int i = 0; i < 26; i++) {
			nodes[i] = new Node(Character.toString((char) (i + 'A')));
		}
		
		String a, b, c;
		for (int i = 1; i <= N; i++) {
			a = input.next();
			b = input.next();
			c = input.next();
			char cha = a.charAt(0);
			char chb = b.charAt(0);
			char chc = c.charAt(0);
			
			if (!b.equals(".")) {
				nodes[cha - 'A'].left = chb - 'A';
				nodes[chb - 'A'].parent = cha - 'A'; 
			}
			
			if (!c.equals(".")) {
				nodes[cha - 'A'].right = chc - 'A';
				nodes[chc - 'A'].parent = cha - 'A'; 
			}
		}
		
		preorder(0);
		System.out.println();
		inorder(0);
		System.out.println();
		postorder(0);
	}
	
	private static void postorder(int i) {
		if (nodes[i].left > -1) {
			postorder(nodes[i].left);
		}
		
		if (nodes[i].right > -1) {
			postorder(nodes[i].right);
		}
		
		System.out.print(nodes[i].val);
	}

	private static void inorder(int i) {
		if (nodes[i].left > -1) {
			inorder(nodes[i].left);
		}
		
		System.out.print(nodes[i].val);
		
		if (nodes[i].right > -1) {
			inorder(nodes[i].right);
		}
	}

	private static void preorder(int i) {
		System.out.print(nodes[i].val);
		
		if (nodes[i].left > -1) {
			preorder(nodes[i].left);
		}
		
		if (nodes[i].right > -1) {
			preorder(nodes[i].right);
		}
	}
}

class Node {
	String val;
    int parent, right, left;
    
    public Node(String val) {
        this.val = val;
        this.parent = this.right = this.left = -1;
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
