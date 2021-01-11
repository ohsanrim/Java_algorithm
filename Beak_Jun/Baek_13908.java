package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_13908 {
	static boolean[] number;
	static int n, m;
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);
		temp = br.readLine().split(" ");
		number = new boolean[10];
		count = 0;
		for (int i = 0; i < m; i++) {
			number[Integer.parseInt(temp[i])] = true;
		}
		dfs(0, 0);
		System.out.println(count);
	}

	private static void dfs(int level, int cnt) {
		if (level >= n) {
			if (m == cnt) {
				count++;
				return;
			}
			return;
		}
		if (n-level < m-cnt)
			return;
		
		for (int i = 0; i <= 9; i++) {
			if (number[i]) {
				number[i] = false;
				dfs(level + 1, cnt + 1);
				number[i] = true;
			} else
				dfs(level + 1, cnt);
		}

	}
}
