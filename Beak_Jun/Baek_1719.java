package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Baek_1719 {
	static ArrayList<Node>[] list;
	static int n, m;
	static boolean[] visited;
	static int[] dis; // 최단거리 계산
	static int[] root; // 지나는 루트 계산
	static int[] answer; // 계산한 표
	static int INF = Integer.MAX_VALUE;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);
		list = new ArrayList[n + 1];
		sb = new StringBuilder();
		for (int i = 0; i < n + 1; i++) {
			list[i] = new ArrayList<Node>();
		}
		for (int i = 0; i < m; i++) {
			temp = br.readLine().split(" ");
			int s = Integer.parseInt(temp[0]);
			int e = Integer.parseInt(temp[1]);
			int t = Integer.parseInt(temp[2]);
			list[s].add(new Node(e, t));
			list[e].add(new Node(s, t));
		}
		for (int i = 1; i < n + 1; i++) {
			dijkstra(i);
		}
		System.out.println(sb);
	}

	private static void dijkstra(int num) {
		// TODO Auto-generated method stub
		clear(); // 계산 전 초기화

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(num, dis[num] = 0));

		while (!pq.isEmpty()) {
			Node n = pq.poll();
			if (visited[n.index])
				continue;
			visited[n.index] = true;
			ArrayList<Node> getNode = list[n.index];
			for (int i = 0; i < getNode.size(); i++) {
				int current = getNode.get(i).index;
				int currentT = getNode.get(i).t + n.t;
				if (dis[current] > currentT) {
					// 갱신
					dis[current] = currentT;
					root[current] = n.index;
					pq.add(new Node(current, dis[current]));
				}
			}
		}
		print(num); // 표 계산해서 출력
	}

	private static void print(int num) {
		// TODO Auto-generated method stub
		for (int i = 1; i < root.length; i++) {
			if (i == num)
				//answer[i] = 0;
				sb.append("- ");
			else {
				int j = i;
				while (true) {
					if (root[j] == num) {
						//answer[i] = j;
						sb.append(j).append(" ");
						break;
					}
					j = root[j];
				}
			}
		}
		/*
		for (int i = 1; i < answer.length; i++) {
			sb.append(answer[i] == 0 ? "-" : answer[i]).append(" ");
		}
		*/
		sb.append("\n");
	}

	private static void clear() {
		// TODO Auto-generated method stub
		dis = new int[n + 1];
		root = new int[n + 1];
		answer = new int[n + 1];
		visited = new boolean[n + 1];
		for (int i = 0; i < n + 1; i++) {
			dis[i] = INF;
		}
	}

	private static class Node implements Comparable<Node> {
		int index, t;

		Node(int index, int t) {
			this.index = index;
			this.t = t;
		}

		@Override
		public int compareTo(Node o) {
			return this.t - o.t;
		}
	}
}
