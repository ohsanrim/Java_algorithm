package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Baek_10282 {
	static int t;
	static ArrayList<Node>[] list;
	static boolean[] visited;
	static int n, d, c;
	static int INF = Integer.MAX_VALUE;
	static int[] dis;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			String[] temp = br.readLine().split(" ");
			n = Integer.parseInt(temp[0]);
			d = Integer.parseInt(temp[1]);
			c = Integer.parseInt(temp[2]);
			list = new ArrayList[n + 1];
			visited = new boolean[n + 1];
			dis = new int[n + 1];
			clear();
			for (int j = 0; j < d; j++) {
				temp = br.readLine().split(" ");
				int s = Integer.parseInt(temp[0]);
				int e = Integer.parseInt(temp[1]);
				int time = Integer.parseInt(temp[2]);
				list[e].add(new Node(s, time));

			}
			dijkstra();
			int max = 0;
			int count = 0;
			for (int k = 1; k < dis.length; k++) {
				if (dis[k] != INF) {
					count++;
					max = Math.max(max, dis[k]);
				}
			}
			sb.append(count + " " + max).append("\n");
		}
		System.out.println(sb);
	}

	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		dis[c] = 0;
		pq.add(new Node(c, dis[c]));
		while (!pq.isEmpty()) {
			Node n = pq.poll();
			if (visited[n.index])
				continue;
			visited[n.index] = true;
			ArrayList<Node> getNode = list[n.index];
			for (int i = 0; i < getNode.size(); i++) {
				int current = getNode.get(i).index;
				int currentTime = n.time + getNode.get(i).time;
				if (currentTime < dis[current]) {
					dis[current] = currentTime;
					pq.add(new Node(current, currentTime));
				}
			}
		}
	}

	private static void clear() {
		for (int i = 0; i <= n; i++) {
			dis[i] = INF;
			list[i] = new ArrayList<Node>();
		}
	}

	private static class Node implements Comparable<Node> {
		int index, time;

		Node(int index, int time) {
			this.index = index;
			this.time = time;
		}

		@Override
		public int compareTo(Node o) {
			return this.time - o.time;
		}
	}
}
