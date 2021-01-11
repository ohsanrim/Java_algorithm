package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Baek_4485 {
	static int N;
	static int[][] map;
	static int[][] dis;
	static int[][] move = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
	static int INF = Integer.MAX_VALUE;
	static int cnt =1;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			map = new int[N][N];
			dis = new int[N][N];
			clear();
			for (int i = 0; i < N; i++) {
				String temp[] = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(temp[j]);
				}
			}
			System.out.println("Problem "+cnt+": " + dijkstra());
			cnt++;
		}
	}

	private static void clear() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dis[i][j] = INF;
			}
		}
	}

	private static int dijkstra() {
		// TODO Auto-generated method stub
		PriorityQueue<Node> pq = new PriorityQueue<>();
		dis[0][0] = map[0][0];
		pq.add(new Node(0, 0, dis[0][0]));
		while (!pq.isEmpty()) {
			Node n = pq.poll();
			for (int i = 0; i < 4; i++) {
				int x = n.x + move[i][0];
				int y = n.y + move[i][1];
				if (isIn(x, y)) {
					int cost = n.cost + map[x][y];
					if (dis[x][y] > cost) {
						dis[x][y] = cost;
						pq.add(new Node(x, y, dis[x][y]));
					}
				}
			}
		}
		return dis[N-1][N-1];
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}

	private static class Node implements Comparable<Node> {
		int x, y, cost;

		Node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.cost - o.cost;
		}
	}
}
/*

5
1 9 1 1 1
1 9 1 9 1
1 1 1 9 1
9 9 9 9 9
9 9 1 1 1

1
4

2
1 0
1 0
*/





