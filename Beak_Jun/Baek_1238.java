package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Baek_1238 {
	static int n, m, x;
	static boolean[] visited;
	static int[] move;
	static ArrayList<Node>[] list;
	static int INF = Integer.MAX_VALUE;
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]); // 시작점
		m = Integer.parseInt(temp[1]);
		x = Integer.parseInt(temp[2]);
		list = new ArrayList[n + 1];
		for (int i = 0; i < n + 1; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			temp = br.readLine().split(" ");
			int s = Integer.parseInt(temp[0]);
			int e = Integer.parseInt(temp[1]);
			int d = Integer.parseInt(temp[2]);
			list[s].add(new Node(e, d));
		}
		for (int i = 1; i <= n; i++) {
			max = Math.max(max, dijkstra(i, x) + dijkstra(x, i));
		}
		System.out.println(max);
	}

	private static int dijkstra(int s, int e) {
		visited = new boolean[n + 1];
		move = new int[n + 1];
		clear();
		// 다익스트림 시작
		PriorityQueue<Node> pq = new PriorityQueue<>();
		move[s] = 0;
		pq.add(new Node(s, move[s]));
		while (!pq.isEmpty()) {
			Node n = pq.poll();
			if (visited[n.index])
				continue;
			visited[n.index] = true;
			ArrayList<Node> getNode = list[n.index];
			for (int i = 0; i < getNode.size(); i++) {
				int current = getNode.get(i).index;
				int currentDis = n.dis + getNode.get(i).dis;
				if (currentDis < move[current]) {
					move[current] = currentDis;
					pq.add(new Node(current, currentDis));
				}
			}
		}
		return move[e];
	}

	private static void clear() {
		for (int i = 0; i < n + 1; i++) {
			move[i] = INF;
		}
	}

	private static class Node implements Comparable<Node> {
		int index, dis;
		int option; // 0일땐 가는길, 1일떈 돌아오는길

		Node(int index, int dis) {
			this.index = index;
			this.dis = dis;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.dis - o.dis;
		}
	}

}
