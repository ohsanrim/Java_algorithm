package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baek_2178 {
	static int [][] map;
	static Boolean [][] check;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] num = br.readLine().split(" ");
		map = new int[Integer.parseInt(num[0])+1][Integer.parseInt(num[1])+1];
		System.out.println();
		for(int i=1;i<map.length;i++) {
			String input = br.readLine();
			for(int j=0;j<map[i].length-1;j++) {
				map[i][j] = input.charAt(j)-48;
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		bfs(1,1);
	}
	public static void bfs(int a, int b) {
		Queue<Integer> que = new LinkedList<Integer>();
	}

}
