package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Baek_5558 {
	static int H, W, N;
	static String[][] map;
	static int[][] move = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
	static ArrayList<Point> cheeze;
	static boolean[][] visited;
	static int shortTime;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String temp[] = br.readLine().split(" ");
		H = Integer.parseInt(temp[0]); // 행
		W = Integer.parseInt(temp[1]); // 열
		N = Integer.parseInt(temp[2]); // 치즈 개수
		map = new String[H][W];
		shortTime = 0; // 최단시간 계산
		cheeze = new ArrayList<Point>();
		/*
		 * 쥐는 둥지에서 출발하여 모든 치즈공장을 방문해야 하고, 방문 시 치즈를 1개씩 먹는다.
		 * 
		 * 이 마을에는 N개의 치즈공장이 있고, 모든 공장은 1종류의 치즈만 생산하고 있다. 치즈의 경도는 공장에 따라 차이가 있으며 경도 1에서 N
		 * 까지의 치즈를 생산하는 치즈공장이 각각 하나씩 있다.
		 * 
		 * 쥐의 초기 체력은 1이며, 치즈를 1개 먹을 때마다 체력이 1 증가한다. 그리고 쥐는 자신의 체력보다 경도가 높은 딱딱한 치즈를 먹을 수
		 * 없다.
		 * 
		 * 쥐는 동서남북에 인접한 부지에 1분에 이동할 수 있지만, 장애물의 부지에 들어갈 수 없다. 치즈공장에서 치즈를 먹지않고 지나칠 수 있다.
		 * 모든 치즈를 먹고 끝내는데 걸리는 최소 시간은?
		 * 
		 * 쥐의 둥지는 "S", 장애물은 "X", 치즈 공장은 "1~9" 공징의 번호가 "K"인 공장은 경도 "K"의 치즈를 1개 생산한다.
		 * 공터는.으로 표시된다. 쥐는 모든 치즈를 먹는 것이 보장되어 있다.
		 * 
		 */
		Point start = new Point(-1, -1);
		for (int i = 0; i < H; i++) {
			temp = br.readLine().split("");
			for (int j = 0; j < W; j++) {
				map[i][j] = temp[j];
				if (map[i][j].equals("S")) {
					start.x = i;
					start.y = j;
					map[i][j] = ".";
				} else if (!map[i][j].equals("X") && !map[i][j].equals(".")) {
					cheeze.add(new Point(i, j, Integer.parseInt(map[i][j])));
				}
			}
		}
		Collections.sort(cheeze);

		while (true) {
			start = bfs(start);
			if (cheeze.size() == 0)
				break;
		}
		System.out.println(shortTime);

	}

	private static Point bfs(Point start) {
		Queue<Point> q = new LinkedList<Point>();
		q.add(start);
		visited = new boolean[H][W];
		visited[start.x][start.y] = true;
		Point cheezePoint = cheeze.get(0);
		cheeze.remove(0);

		for (int time = 1; !q.isEmpty(); time++) {
			int qSize = q.size();
			for (int j = 0; j < qSize; j++) {
				Point p = q.poll();
				for (int i = 0; i < 4; i++) {
					int x = p.x + move[i][0];
					int y = p.y + move[i][1];
					if (isIn(x, y) && !map[x][y].equals("X") && !visited[x][y]) {
						// 지나갈 수 있을 때
						if (cheezePoint.x == x && cheezePoint.y == y) {
							shortTime += time;
							return new Point(x, y);
						}
						q.add(new Point(x, y));
						visited[x][y] = true;
					}
				}
			}
		}
		return null;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < H && c < W;
	}

	private static class Point implements Comparable<Point> {
		int x, y, hard;

		Point(int x, int y, int hard) {
			this.x = x;
			this.y = y;
			this.hard = hard; //치즈 경도
		}

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			return hard - o.hard;
		}
	}

}
