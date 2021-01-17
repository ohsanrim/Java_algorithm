package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baek_15685 {
	static int n;
	static int X1, Y1; // 초기값
	static int X2, Y2; // 끝값
	static boolean[][] map;
	static int[][] move = { { 1, 0 }, { 0, -1 }, { -1, 0 }, { 0, 1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new boolean[101][101];
		for (int i = 0; i < n; i++) {
			String[] temp = br.readLine().split(" ");
			X1 = Integer.parseInt(temp[0]);
			Y1 = Integer.parseInt(temp[1]);
			int d = Integer.parseInt(temp[2]);
			int g = Integer.parseInt(temp[3]);
			solution(d, g);
		}
		System.out.println(check());
	}

	private static int check() {
		int cnt = 0;
		for (int i = 0; i < map.length - 1; i++) {
			for (int j = 0; j < map[i].length - 1; j++) {
				if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1])
					cnt++;
			}
		}
		return cnt;
	}

	private static void solution(int d, int g) {
		Queue<int[]> q = new LinkedList<>();
		// 0세대 넣기
		q.add(new int[] { X1, Y1 });
		map[Y1][X1] = true;
		X2 = X1 + move[d][0]; // 끝점 x
		Y2 = Y1 + move[d][1]; // 끝점 y
		q.add(new int[] { X2, Y2 });
		map[Y2][X2] = true;
		
		for (int i = 0; i < g; i++) {
			int q_size = q.size();
			int tempX = 0;  
			int tempY = 0;
			for (int j = 0; j < q_size; j++) {
				// i세대 계산
				int[] point = q.poll();
				int nextX = X2 - (point[1] - Y2);
				int nextY = Y2 + (point[0] - X2);
				map[nextY][nextX] = true;
				q.add(point);
				q.add(new int[] { nextX, nextY });
				if (point[0] == X1 && point[1] == Y1) {  //끝 값일 때
					tempX = nextX;
					tempY = nextY;
				}
			}
			X2 = tempX;
			Y2 = tempY;
		}
	}
}
