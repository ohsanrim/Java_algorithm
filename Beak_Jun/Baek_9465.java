package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_9465 {
	public static String input[][];
	public static int[][] calMax;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int line = Integer.parseInt(br.readLine());
			input = new String[2][line];
			String[] temp = br.readLine().split(" ");
			input[0] = temp;
			temp = br.readLine().split(" ");
			input[1] = temp;
			calMax = new int[2][line];
			clear();
			int answer = Math.max(dp(0, line), dp(1, line));
			System.out.println(answer);
		}
	}

	private static void clear() {
		// TODO Auto-generated method stub
		for (int i = 0; i < calMax.length; i++) {
			for (int j = 0; j < calMax[i].length; j++) {
				calMax[i][j] = -1;
			}
		}
		calMax[0][0] = Integer.parseInt(input[0][0]);
		calMax[1][0] = Integer.parseInt(input[1][0]);
	}

	private static int dp(int line, int level) {
		if (level <= 0) {
			return 0;
		} else if (level == 1 || calMax[line][level - 1] != -1) {
			return calMax[line][level - 1];
		} else {
			int newLine = line == 0 ? 1 : 0;
			int temp = Integer.parseInt(input[line][level - 1]);
			temp += Math.max(dp(newLine, level - 1), dp(newLine, level - 2));
			calMax[line][level - 1] = temp;
			return temp;
		}
	}
}
