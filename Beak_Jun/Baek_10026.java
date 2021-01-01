package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baek_10026 {
	static int n;
	static char[][] map;
	static boolean[][] visited;
	static int[][] move = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static int index;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		visited = new boolean[n][n];  //0은 적록색약 1은 일반
		for (int i = 0; i < n; i++) {
			String[] temp = br.readLine().split("");
			for (int j = 0; j < n; j++) {
				map[i][j] = temp[j].charAt(0);
			}
		}
		System.out.println(normal()+" "+redGreen());
	}
	private static int normal() {
		Queue<Point> q = new LinkedList<>();
		index = 0;
		while(true) {
			q.add(check());
			if(q.peek().x==-1) break;
			while (!q.isEmpty()) {
				Point p = q.poll();
				for (int j = 0; j < 4; j++) {
					int x = p.x + move[j][0];
			 		int y = p.y + move[j][1];
					if (isIn(x, y) && !visited[x][y]) {
						if (map[p.x][p.y]==map[x][y]) {
							q.add(new Point(x,y));
							visited[x][y]=true;
						}
					}
				}
			}
			index++;
		}
		return index;
	}

	private static int redGreen() {
		Queue<Point> q = new LinkedList<>();
		index = 0;
		visited = new boolean[n][n];  
		
		while(true) {
			q.add(check());
			if(q.peek().x==-1) break;
			while (!q.isEmpty()) {
				Point p = q.poll();
				for (int j = 0; j < 4; j++) {
					int x = p.x + move[j][0];
			 		int y = p.y + move[j][1];
					if (isIn(x, y) && !visited[x][y]) {
						if (!(map[p.x][p.y] != map[x][y] && (map[p.x][p.y]=='B' || map[x][y]=='B'))) {
							q.add(new Point(x,y));
							visited[x][y]=true;
						}
					}
				}
			}
			index++;
		}
		return index;
	}

	private static Point check() {
		
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(!visited[i][j]) {
						visited[i][j]=true;
						return new Point(i,j);
					}
				}
			}
		return new Point(-1,-1);
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < n && y < n;
	}
	private static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
			//this.section = section;
		}
	}
}
/*
 
 
8
RRRBBBRR
RRRBBBRR 
GGRRBBBB
BBBBGGRR
BBGGRRBB 
GRGRGRGR 
GRGRGRGR 
BBGRBBGR



 * 
 * 
 */