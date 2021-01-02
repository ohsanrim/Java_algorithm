package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Baek_19236 {
	static int[][] move = { { -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 } };
	//static ; // 상어 좌표
	static HashMap<Integer, Point> fish = new HashMap();
	static int[][] map = new int[4][4];
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int sharkEat = 0;
		Point shark= new Point(0,0,0);
		for (int i = 0; i < 4; i++) {
			String[] temp = br.readLine().split(" ");
			for (int j = 0; j < temp.length; j += 2) {
				int fishNum = Integer.parseInt(temp[j]);
				int direction = Integer.parseInt(temp[j + 1]);
				if (i == 0 && j == 0) { // 상어 시작값
					shark = new Point(0, 0, direction);
					sharkEat += fishNum;
				} else {
					map[i][j / 2] = fishNum;
					fish.put(fishNum, new Point(i, j / 2, direction));
				}
			}
		}
		
		dfs(shark,sharkEat);
		System.out.println(max);
	}

	private static void dfs(Point shark, int sharkEat) {
		fishChange(shark); // 물고기 재배치
		// 상어 먹이사냥 시작
		int direction = shark.direction;
		int x = shark.x;
		int y = shark.y;
		boolean check = false;  //계산 종료 체크
		
		while (true) {
			x += move[direction - 1][0];
			y += move[direction - 1][1];
			
			if (isIn(x, y) && map[x][y] != 0) {
				int fishNum = map[x][y];
				int fishDirection = fish.get(fishNum).direction;
				//복제 작업
				int[][] copy_Map = new int[4][4];
				HashMap<Integer, Point> copy_Fish = new HashMap();
				copy(copy_Map, copy_Fish);  //fish와 map 복제 함수
				
				fish.remove(fishNum);
				map[x][y] = 0;
				
				dfs(new Point(x,y,fishDirection),sharkEat + fishNum);
				//복원작업(백트래킹)
				rollback(copy_Map, copy_Fish);
				check = true;
			} else if(!isIn(x, y)) break;
		}
		if (!check) {
			// 더이상 진행방행으로 먹을 수 있는 물고기 없음
			max = Math.max(max, sharkEat);
			return;
		}
		return;
	}

	private static void rollback(int[][] copy_Map, HashMap<Integer, Point> copy_Fish) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				map[i][j] = copy_Map[i][j];
			}
		}
		fish.clear();
		for (Map.Entry<Integer, Point> data : copy_Fish.entrySet()) {
			int x = data.getValue().x;
			int y = data.getValue().y;
			int direction = data.getValue().direction;
			fish.put(data.getKey(), new Point(x, y, direction));
		}
	}

	private static void copy(int[][] copy_Map, HashMap<Integer, Point> copy_Fish) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				copy_Map[i][j] = map[i][j];
			}
		}
		for (Map.Entry<Integer, Point> data : fish.entrySet()) {
			int x = data.getValue().x;
			int y = data.getValue().y;
			int direction = data.getValue().direction;
			copy_Fish.put(data.getKey(), new Point(x, y, direction));
		}
	}

	private static void fishChange(Point shark) {
		for (int i = 1; i <= 16; i++) { // 1~16번 물고기 탐색
			if (fish.containsKey(i)) {
				Point f = fish.get(i);
				for (int j = 0; j < 8; j++) {
					int direction = f.direction;
					int x = f.x + move[direction - 1][0];
					int y = f.y + move[direction - 1][1];
					if (isIn(x, y) && !(shark.x == x && shark.y == y)) {
						// 갈 수 있는 칸일 때
						if (map[x][y] != 0) {
							int fishNumTemp = map[x][y];
							map[f.x][f.y] = fishNumTemp;
							Point changeFish = fish.get(fishNumTemp);
							fish.put(fishNumTemp, new Point(f.x, f.y, changeFish.direction));
						} else {
							map[f.x][f.y] = 0;
						}
						map[x][y] = i;
						fish.put(i, new Point(x, y, direction));
						break;
					} else {
						// 갈 수 없을 때 방향 전환
						f.direction = direction == 8 ? 1 : direction + 1;
					}
				}
			}
		}
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < 4 && c < 4;
	}

	private static class Point {
		int x, y, fishNum;
		int direction; // 1~8까지의 방향

		Point(int x, int y, int direction) {
			this.x = x;
			this.y = y;
			this.direction = direction;
		}
	}
}
/*
package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Baek_19236 {
	static int[][] move = { { -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 } };
	static Point shark; // 상어 좌표
	static HashMap<Integer, Point> fish = new HashMap();
	static int[][] map = new int[4][4];
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int sharkEat = 0;
		for (int i = 0; i < 4; i++) {
			String[] temp = br.readLine().split(" ");
			for (int j = 0; j < temp.length; j += 2) {
				int fishNum = Integer.parseInt(temp[j]);
				int direction = Integer.parseInt(temp[j + 1]);
				if (i == 0 && j == 0) { // 상어 시작값
					shark = new Point(0, 0, direction);
					sharkEat += fishNum;
				} else {
					map[i][j / 2] = fishNum;
					fish.put(fishNum, new Point(i, j / 2, direction));
				}
			}
		}
		dfs(0,0,sharkEat);
		System.out.println(max);
	}

	private static void dfs(int x, int y, int sharkEat) {
		fishChange(); // 물고기 재배치
		// 상어 먹이사냥 시작
		int direction = shark.direction;
		x += move[direction - 1][0];
		y += move[direction - 1][1];
		boolean check = false;
		while (isIn(x, y)) {
			if (map[x][y] != 0) {
				int temp = map[x][y];
				Point tempP = new Point(shark.x, shark.y, direction);
				shark = new Point(x, y, fish.get(temp).direction);

				int[][] copy_Map = new int[4][4];
				HashMap<Integer, Point> copy_Fish = new HashMap();
				copy(copy_Map, copy_Fish);
				fish.remove(temp);
				map[x][y] = 0;
				dfs(x,y,sharkEat + temp);
				shark = new Point(tempP.x, tempP.y, tempP.direction);
				rollback(copy_Map, copy_Fish);
				check = true;
			}
			x += move[direction - 1][0];
			y += move[direction - 1][1];
		}
		if (!check) {
			// 더이상 진행방행으로 먹을 수 있는 물고기 없음
			max = Math.max(max, sharkEat);
			return;
		}
		return;
	}

	private static void rollback(int[][] copy_Map, HashMap<Integer, Point> copy_Fish) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				map[i][j] = copy_Map[i][j];
			}
		}
		fish.clear();
		for (Map.Entry<Integer, Point> data : copy_Fish.entrySet()) {
			int x = data.getValue().x;
			int y = data.getValue().y;
			int direction = data.getValue().direction;
			fish.put(data.getKey(), new Point(x, y, direction));
		}
	}

	private static void copy(int[][] copy_Map, HashMap<Integer, Point> copy_Fish) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				copy_Map[i][j] = map[i][j];
			}
		}
		for (Map.Entry<Integer, Point> data : fish.entrySet()) {
			int x = data.getValue().x;
			int y = data.getValue().y;
			int direction = data.getValue().direction;
			copy_Fish.put(data.getKey(), new Point(x, y, direction));
		}
	}

	private static void fishChange() {
		for (int i = 1; i <= 16; i++) { // 1~16번 물고기 탐색
			if (fish.containsKey(i)) {
				Point f = fish.get(i);
				for (int j = 0; j < 8; j++) {
					int direction = f.direction;
					int x = f.x + move[direction - 1][0];
					int y = f.y + move[direction - 1][1];
					if (isIn(x, y) && !(shark.x == x && shark.y == y)) {
						// 갈 수 있는 칸일 때
						if (map[x][y] != 0) {
							int fishNumTemp = map[x][y];
							map[f.x][f.y] = fishNumTemp;
							Point changeFish = fish.get(fishNumTemp);
							fish.put(fishNumTemp, new Point(f.x, f.y, changeFish.direction));
						} else {
							map[f.x][f.y] = 0;
						}
						map[x][y] = i;
						fish.put(i, new Point(x, y, direction));
						break;
					} else {
						// 갈 수 없을 때 방향 전환
						f.direction = direction == 8 ? 1 : direction + 1;
					}
				}
			}
		}
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < 4 && c < 4;
	}

	private static class Point {
		int x, y, fishNum;
		int direction; // 1~8까지의 방향

		Point(int x, int y, int direction) {
			this.x = x;
			this.y = y;
			this.direction = direction;
		}
	}
}




*/
