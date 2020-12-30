package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Beak_16469 {
	static int r, c;
	static boolean[][][] visited;
	static int[][] map;
	static ArrayList<Villain> villain;
	static int[][] move = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } }; // 우좌상하
	static int[][] timeMap;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		r = Integer.parseInt(temp[0]);
		c = Integer.parseInt(temp[1]);
		visited = new boolean[3][r][c];
		map = new int[r][c];
		timeMap = new int[r][c];

		for (int i = 0; i < r; i++) {
			temp = br.readLine().split("");
			for (int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(temp[j]);
			}
		}
		villain = new ArrayList<Villain>();

		for (int i = 0; i < 3; i++) {
			temp = br.readLine().split(" ");
			int x = Integer.parseInt(temp[0]) - 1;
			int y = Integer.parseInt(temp[1]) - 1;
			villain.add(new Villain(x, y, i));
			visited[i][x][y]=true;
		}
		bfs();
		count();
	}

	private static void count() {
		int count=0;
		int min=1000000000;
		int time=min;
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				if(visited[0][i][j] && visited[1][i][j] && visited[2][i][j]) {
					if(timeMap[i][j]!=0 && time>timeMap[i][j]) {
						//더 짧은 시간일 때
						time=timeMap[i][j];
						count=1;
					} else if(time==timeMap[i][j]) count++;
				}
			}
		}
		System.out.println(time==min?-1:time);
		System.out.println(time==min?"":count);
	}
	
	private static void bfs() {
		// TODO Auto-generated method stub
		Queue<Villain> q = new LinkedList<>(villain);
		for (int time = 1; !q.isEmpty(); time++) {
			int q_size = q.size();
			for (int i = 0; i < q_size; i++) {
				Villain v = q.poll();
				for (int j = 0; j < 4; j++) {
					int x = v.x + move[j][0];
					int y = v.y + move[j][1];
					if (isIn(x, y) && !visited[v.num][x][y] && map[x][y] != 1) {
						q.add(new Villain(x, y, v.num));
						timeMap[x][y] = time;
						visited[v.num][x][y] = true;
					}
				}

			}
		}
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < r && y < c;
	}

	private static class Villain {
		int x, y;
		int num; // 악당번호 0:넉살/ 1: 스윙스 / 2: 창모

		Villain(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}

}
/*

4 4 
0000 
0000 
0000 
0000 
1 1 
4 4 
1 4

 */
