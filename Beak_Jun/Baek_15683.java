package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baek_15683 {
	static int n, m;
	static int[][] map;
	static ArrayList<Point> list;
	static int min = Integer.MAX_VALUE;
	static boolean[][] cctv;
	static int[][] move = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String temp[] = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);
		list = new ArrayList<>();
		map = new int[n][m];
		cctv = new boolean[n][m];
		int cnt = 0;  //cctv와 벽 개수(사각지대 체크 안할 부분)
		for (int i = 0; i < n; i++) {
			temp = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(temp[j]);
				if (map[i][j] != 0)
					cnt++;  
				if (map[i][j] >= 1 && map[i][j] <= 5) {
					list.add(new Point(i, j, map[i][j]));
				}
			}
		}
		dfs(0, 0);
		System.out.println(min - cnt);
	}

	private static void dfs(int level, int cal) {
		if (level == list.size()) {
			// 끝났을 경우
			min = Math.min(min, n * m - cal);
			return;
		}
		//시시티비 계산 시작
		boolean[][] tempV = new boolean[n][m];
		tempV = copy(tempV);
		int calTemp = 0;
		Point p = list.get(level);
		if (p.pos == 2) {  //2번 cctv
			for (int j = 0; j < 2; j++) {
				calTemp = watch(p.x, p.y, move[j][0], move[j][1]) + watch(p.x, p.y, move[j + 2][0], move[j + 2][1]);
				dfs(level + 1, cal + calTemp);
				rollback(tempV);
			}
			return;
		} else if (p.pos == 5) {  //5번 cctv
			for (int i = 0; i < 4; i++) {
				calTemp += watch(p.x, p.y, move[i][0], move[i][1]);
			}
			dfs(level + 1, cal + calTemp);
			rollback(tempV);
			return;
		} else {
			for (int i = 0; i < 4; i++) {
				calTemp = 0;
				if (p.pos == 1) {  //1번 cctv 
					calTemp = watch(p.x, p.y, move[i][0], move[i][1]);
				} else if (p.pos == 3) {  //3번 cctv 
					calTemp = watch(p.x, p.y, move[i][0], move[i][1])
							+ watch(p.x, p.y, move[i == 3 ? 0 : i + 1][0], move[i == 3 ? 0 : i + 1][1]);
				} else if (p.pos == 4) {  //4번 cctv 
					for (int j = 0; j < 4; j++) {
						if (i != j) {
							calTemp += watch(p.x, p.y, move[j][0], move[j][1]);
						}
					}
				}
				dfs(level + 1, cal + calTemp);
				rollback(tempV);
			}
		}
	}

	private static void rollback(boolean[][] tempV) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				cctv[i][j] = tempV[i][j];
			}
		}
	}

	private static int watch(int x, int y, int i, int j) {
		int cnt = 0;
		while (true) {
			x += i;
			y += j;
			if (!isIn(x, y) || map[x][y] == 6)
				break;
			if (!cctv[x][y]) {
				cctv[x][y] = true;
				if (map[x][y] == 0)
					cnt++;
			}

		}
		return cnt;
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < n && y < m;
	}

	private static boolean[][] copy(boolean[][] tempV) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				tempV[i][j] = cctv[i][j];
			}
		}
		return tempV;
	}

	private static class Point {
		int x, y;
		int pos; // 몇개의 면을 감시해야 하는지

		Point(int x, int y, int pos) {
			this.x = x;
			this.y = y;
			this.pos = pos;
		}
	}
}