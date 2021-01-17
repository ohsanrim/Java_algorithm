package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Baek_16234 {
	static int n, l, r;
	static int[][] map;
	static boolean[][] visited;
	static int cnt = 0;
	static int[][] move = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		l = Integer.parseInt(temp[1]);
		r = Integer.parseInt(temp[2]);
		map = new int[n][n];
		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			temp = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(temp[j]);
			}
		}
		while (true) {
			boolean check = false;
			visited = new boolean[n][n];
			//System.out.println("들어와??");
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (!visited[i][j]) {
						if(bfs(new Point(i,j))) {
							check=true;
						}
					}
				}
			}
			if (!check)
				break;
			cnt++;
		}
		System.out.println(cnt);
	}
	
	private static boolean bfs(Point o) {
		boolean check= false;;
		Queue<Point> q = new LinkedList<>();
		ArrayList <Point>list = new ArrayList<>();
		q.add(o);
		visited[o.x][o.y]=true;
		list.add(o);
		int sum = map[o.x][o.y];
		int count = 1;
		
		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 4; i++) {
				int x = p.x + move[i][0];
				int y = p.y + move[i][1];
				if (isIn(x, y) && !visited[x][y] && check(p.x, p.y, x, y)) {
					// 국경선 오픈
					q.add(new Point(x, y));
					visited[x][y] = true;
					list.add(new Point(x,y));
					sum += map[x][y];
					count++;
					check = true;
				}
			}
		}
		
		System.out.println("sum:"+sum +" "+"count: "+count);
		sum/=count;
		for(Point p : list) {
			map[p.x][p.y]=sum;
		}
		print();
		return check;
	}

	private static void print() {
		// TODO Auto-generated method stub
		System.out.println("----------------------------");
		for (int[] arr : map) {
			for (int data : arr) {
				System.out.print(data + " ");
			}
			System.out.println();
		}
		System.out.println("----------------------------");
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < n && y < n;
	}

	private static boolean check(int x, int y, int x2, int y2) {
		int cal = Math.abs(map[x][y] - map[x2][y2]);
		return cal >= l && cal <= r;
	}

	private static Point searchPoint() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					visited[i][j]=true;
					return new Point(i, j);
				}
					
			}
		}
		return new Point(-1, -1);
	}

	private static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
/*
2 10 20 
0 30
50 10
//3

5 1 5
88 27 34 84 9
40 91 11 30 81
2 88 65 26 75
75 66 16 14 28
89 10 5 30 75

//1
 */
