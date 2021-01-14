package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baek_15683 {
	static int n, m;
	static int[][] map;
	static ArrayList<int[]> list;
	static int min = Integer.MAX_VALUE;
	static boolean[][] cctv;
	static int[][] move = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
	static int [][][] cctvMove= {{{0},{1},{2},{3}},{{0,2},{1,3}},{{0,1},{1,2},{2,3},{3,0}},
			{{0,1,2},{1,2,3},{0,2,3},{0,1,3}},{{0,1,2,3}}};
	

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
					list.add(new int[] {i,j,map[i][j]-1});
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
		copy(tempV);
		int [] point = list.get(level);
		int x=point[0];
		int y=point[1];
		int pos = point[2];
		for(int i=0;i<cctvMove[pos].length;i++) {
			int calTemp = 0;
			for(int j=0;j<cctvMove[pos][i].length;j++) {
				calTemp+=watch(x,y,move[cctvMove[pos][i][j]][0], move[cctvMove[pos][i][j]][1]);
			}
			dfs(level + 1, cal + calTemp);
			rollback(tempV);
		}
	}
	
	private static void copy(boolean[][] tempV) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				tempV[i][j] = cctv[i][j];
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
		while (isIn(x, y) && map[x][y] != 6) {
			if (!cctv[x][y]) {
				cctv[x][y] = true;
				if (map[x][y] == 0)
					cnt++;
			}
			x += i;
			y += j;
		}
		return cnt;
	}
	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < n && y < m;
	}
}