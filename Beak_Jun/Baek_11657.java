package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baek_11657 {
	static int n, m;
	static int INF = Integer.MAX_VALUE;
	static long[] dis;
	static ArrayList<Node>[] list;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);
		list = new ArrayList[n + 1];
		dis = new long[n + 1];
		for (int i = 1; i < n + 1; i++) {
			list[i] = new ArrayList<>();
			dis[i] = INF;
		}
		for (int i = 0; i < m; i++) {
			temp = br.readLine().split(" ");
			int s = Integer.parseInt(temp[0]);
			int e = Integer.parseInt(temp[1]);
			int t = Integer.parseInt(temp[2]);
			list[s].add(new Node(e, t));
		}
		if (bellman_ford()) {
			for (int i = 2; i < n + 1; i++) {
				System.out.println(dis[i] == INF ? "-1" : dis[i]);
			}
		} else {
			System.out.println(-1);
		}
	}

	private static boolean bellman_ford() {
		// TODO Auto-generated method stub
		
		dis[1] = 0;
		boolean check = false;
		//System.out.println(n);
		for (int i = 1; i < n + 1; i++) {
			check = false;
			for (int j = 1; j < n + 1; j++) {
				for (Node node : list[j]) {
					//System.out.println(dis[j] + node.t+" "+ dis[node.index]);
					if (dis[j] != INF && dis[j] + node.t < dis[node.index]) {
						dis[node.index] = dis[j] + node.t;
						check = true;
					}
				}
			}
			if (!check) {
				break;
			}
		}
		if (check)  //계속 갱신이 발생할 경우
			return false;
		return true;
	}

	private static class Node {
		int index;
		int t; // 소요시간

		Node(int index, int t) {
			this.index = index;
			this.t = t;
		}
	}
}
