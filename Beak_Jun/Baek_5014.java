package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baek_5014 {
	static int f, s, g, u, d;
	static int[] elevator;
	static boolean[] visited;
	static int min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		f = Integer.parseInt(temp[0]);
		s = Integer.parseInt(temp[1]);
		g = Integer.parseInt(temp[2]);
		u = Integer.parseInt(temp[3]);
		d = Integer.parseInt(temp[4]);
		elevator = new int[f + 1];
		visited = new boolean[f + 1];
		System.out.println(bfs()?min:"use the stairs");
	}

	private static boolean bfs() {
		// TODO Auto-generated method stub
		Queue<Integer> q = new LinkedList<>();
		q.add(s);
		visited[s] = true;
		for (int time = 0; !q.isEmpty(); time++) {
			int qSize = q.size();
			for (int i = 0; i < qSize; i++) {
				int current = q.poll(); // 현재 위치
				if(current==g) {
					min= time;
					return true;
				}
				int up = current + u;
				int down = current - d;
				if(isIn(up) && !visited[up]) {
					visited[up]=true;
					q.add(up);
				}
				if(isIn(down) && !visited[down]) {
					visited[down]=true;
					q.add(down);
				}
			}
		}
		return false;
	}

	private static boolean isIn(int level) {
		return level>0 && level<=f;
	}
}
/*
10 1 1 9 1

10 1 10 9 1
*/
