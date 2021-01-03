package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Baek_1753 {
	static ArrayList<Node>[] list;
	static int v, e, k;
	static int[] distance;
	static int INF = Integer.MAX_VALUE; // 무한
	static boolean visited[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		String[] temp = br.readLine().split(" ");
		v = Integer.parseInt(temp[0]); // 정점 개수
		e = Integer.parseInt(temp[1]); // 간선 개수
		k = Integer.parseInt(br.readLine()); // 시작 좌표
		distance = new int[v + 1];
		list = new ArrayList[v + 1];
		visited = new boolean[v+1];
		for (int i = 0; i < distance.length; i++) {
			distance[i] = INF;
			list[i] = new ArrayList<>();
		}

		for (int i = 1; i < e+1; i++) {
			temp = br.readLine().split(" ");
			int start = Integer.parseInt(temp[0]);
			int end = Integer.parseInt(temp[1]);
			int dis = Integer.parseInt(temp[2]);
			list[start].add(new Node(end, dis));
		}
		solution(k);
		for (int i = 1; i < distance.length; i++) {
			sb.append(distance[i] == INF ? "INF": distance[i]).append("\n");
		}
		System.out.println(sb);
	}

	private static void solution(int k2) {
		distance[k2] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(k2, distance[k2]));
		while (!pq.isEmpty()) {
			Node n = pq.poll();
			ArrayList<Node> getNode = list[n.index];
			if (visited[n.index])
				continue;
			visited[n.index]=true;
			for (int i = 0; i < getNode.size(); i++) {
				int current = getNode.get(i).index;
				int currentDis = getNode.get(i).dis + n.dis;
				if (distance[current] > currentDis) {
					distance[current] = currentDis;
					pq.offer(new Node(current, currentDis));
				}
			}
		}
	}

	private static class Node implements Comparable<Node> {
		int index, dis;

		Node(int index, int dis) {
			this.index = index;
			this.dis = dis;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.dis-o.dis;
		}
	}
}
/*
 * 메모리 초과 1. 저장된 최소거리보다 큰 거리ㅣ의 값이 뷰에 들어오면 계산을 무시한다. 2. 거리가 짧은 경우가 나와 갱신하게 될 때에만
 * 큐에 값을 넣어준다. 시간 초과
6 9
1
1 2 10
1 3 30
1 4 15
2 5 20
3 6 5
4 3 5
4 6 20
6 4 20
5 6 20



2 3
1
1 2 3
1 2 1
1 2 4

30% 틀림
5 6
1
4 1 1
1 2 2
1 3 3
2 3 4
2 5 5
3 5 6

73% 틀림
3 2
3
1 3 10
2 1 4

2 1
2
2 1 1

4 8
1
1 2 3
2 1 5
4 3 4
2 3 10
1 3 10
2 4 1
3 1 1
1 2 2

정답

0

2

7

3
 */
