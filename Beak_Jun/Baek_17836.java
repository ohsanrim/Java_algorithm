package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baek_17836 {
	static int n, m, t;
	static boolean[][][] visited;
	static int[][] map;
	static int[][] move = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);
		t = Integer.parseInt(temp[2]);
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			temp = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(temp[j]);
			}
		}
		int time = dfs();
		System.out.println(time==-1||time>t?"Fail":time);
	}

	private static int dfs() {

		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(0, 0, 0));
		visited = new boolean[2][n][m];
		visited[0][0][0]=true;
		
		for (int time = 0; !q.isEmpty(); time++) {
			int q_size = q.size();
			for (int i = 0; i < q_size; i++) {
				Point p = q.poll();
				if(p.x==n-1&&p.y==m-1) { //공주 만남					
					return time;
				}
				for (int j = 0; j < 4; j++) {
					int x = p.x + move[j][0];
					int y = p.y + move[j][1];
					int weapon = p.weapon;
					if(isIn(x,y) && !visited[weapon][x][y]) {
						if(map[x][y]==2) {
							//무기 발견했을 때
							q.add(new Point(x,y,weapon+1));
							visited[weapon+1][x][y] = true;
						} else if(map[x][y]==0 || weapon==1){
							//벽이 아니거나 무기 들고 있을 때
							q.add(new Point(x,y,weapon));
							visited[weapon][x][y] = true;
						}
					}
				}
			}
		}
		return -1;
	}
	
	private static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < n && c < m;
	}

	private static class Point {
		int x, y;
		int weapon; // gram이 있는지(1) 없는지(0) 여부

		Point(int x, int y, int weapon) {
			this.x = x;
			this.y = y;
			this.weapon = weapon;
		}
	}
}
/*

3 3 100
0 1 2 
0 1 1
0 0 0

*/
