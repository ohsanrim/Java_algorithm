package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Baek_2206 {
	static int n, m;
	static int[][] map;
	static boolean[][][] visited;
	static int min;
	static int[][] move = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);
		map = new int[n][m];
		min = 1000000000;

		for (int i = 0; i < n; i++) {
			temp = br.readLine().split("");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(temp[j]);
			}
		}
		System.out.println(bfs());
	}

	private static int bfs() {
		visited = new boolean[2][n][m];
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(0, 0, 0));

		for (int count = 1; !q.isEmpty(); count++) { // count로 몇개의 길을 지나는지 체크
			int q_size = q.size();
			while (q_size > 0) {
				Point p = q.poll();
				visited[0][0][0] = true;
				if (p.x == n - 1 && p.y == m - 1) {  //계산 종료
					return count;
				}
				for (int i = 0; i < 4; i++) {
					int x = p.x + move[i][0];
					int y = p.y + move[i][1];
					int wallLine = p.wallLine;  //0 또는 1이 들어가있음(0: 벽을 아직 뚫지 않음/ 1: 벽을 뚫음)

					if (isIn(x, y) && !visited[wallLine][x][y]) {  //영역 안인지 체크, 방문한 길인지 체크
						if (map[x][y] == 0) {  //지나갈 수 있는 길일 때
							q.add(new Point(x, y, wallLine));
							visited[wallLine][x][y] = true;
						} else if (wallLine == 0) {
							// 벽을 마주쳤지만, 벽을 아직 부수지 않은 경우
							q.add(new Point(x, y, 1));
							visited[1][x][y] = true;
						}
					}
				}
				q_size--;
			}
		}
		return -1;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < n && c < m;
	}

	private static class Point {
		int x, y;
		int wallLine;

		Point(int x, int y, int wallLine) {
			this.x = x;
			this.y = y;
			this.wallLine = wallLine;
		}
	}
}
