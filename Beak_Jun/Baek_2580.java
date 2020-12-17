package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baek_2580 {
	public static int[][] board; // 스도쿠 보드
	public static Boolean end = false;
	public static ArrayList<int[]> list; // 채워야 할 곳의 좌표값

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new int[9][9];
		list = new ArrayList<int[]>();
		for (int i = 0; i < 9; i++) {
			String temp[] = br.readLine().split(" ");
			for (int j = 0; j < 9; j++) {
				int input = Integer.parseInt(temp[j]);
				board[i][j] = input;
				if (input == 0)
					list.add(new int[] { j, i });
			}
		}
		back(0);
	}

	private static void print() {
		for (int[] data : board) {
			for (int answer : data) {
				System.out.print(answer + " ");
			}
			System.out.println();
		}
	}

	private static void back(int level) {
		if (level == list.size()) {
			// 계산 끝
			end = true;
			print();
		} else {
			int x = list.get(level)[0];
			int y = list.get(level)[1];
			Boolean[] check = check(x, y);
			for (int i = 1; i <= 9; i++) {
				if (!check[i] && !end) {
					board[y][x] = i;
					back(level + 1);
					board[y][x] = 0;
				}
			}
		}
	}

	private static Boolean[] check(int x, int y) {
		// 초기화
		Boolean[] check = new Boolean[10];
		for (int i = 0; i < 10; i++) {
			check[i] = false;
		}
		for (int i = 0; i < 9; i++) {
			check[board[y][i]] = true; // 가로 체크
			check[board[i][x]] = true; // 세로 체크
		}
		for (int i = 3 * (y / 3); i < 3 * (y / 3) + 3; i++) {
			for (int j = 3 * (x / 3); j < 3 * (x / 3) + 3; j++) {
				check[board[i][j]] = true;
			}
		}
		return check;
	}
}
