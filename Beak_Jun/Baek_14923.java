package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baek_14923 {
	static int n, m;
	static Point start, end;
	static int[][] map;
	static boolean[][][] visited;
	static int[][] move = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);
		map = new int[n][m];
		visited = new boolean[2][n][m];
		temp = br.readLine().split(" ");
		start = new Point(Integer.parseInt(temp[0])-1, Integer.parseInt(temp[1])-1);
		temp = br.readLine().split(" ");
		end = new Point(Integer.parseInt(temp[0])-1, Integer.parseInt(temp[1])-1);
		for (int i = 0; i < n; i++) {
			temp = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(temp[j]);
			}
		}
		int answer = bfs();
		System.out.println(answer);
	}

	private static int bfs() {
		Queue<Point> q = new LinkedList<>();
		q.add(start);
		visited[0][start.x][start.y] = true;
		for (int time = 0; !q.isEmpty(); time++) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point p = q.poll();
				if(p.x==end.x && p.y==end.y) return time;
				for (int j = 0; j < 4; j++) {
					int x = p.x + move[j][0];
					int y = p.y + move[j][1];
					if (isIn(x, y) && !visited[p.num][x][y]) {
						if (map[x][y] == 1 && p.num == 0) {
							q.add(new Point(x, y, 1));
							visited[1][x][y] = true;
						} else if(map[x][y]==0){
							//이동가능
							q.add(new Point(x, y, p.num));
							visited[p.num][x][y] = true;
						}
					}
				}
			}
		}
		return -1;
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 & y >= 0 && x < n && y < m;
	}

	private static class Point {
		int x, y, num;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		Point(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}

}
/*

5 6
1 1
5 6
0 1 1 1 0 0
0 1 1 0 0 0
0 1 0 0 1 0
0 1 0 0 1 0
0 0 0 1 1 0

5 6
1 1
2 1
0 1 1 1 0 0
0 1 1 0 0 0
0 1 0 0 1 0
0 1 0 0 1 0
0 0 0 1 1 0
*/
