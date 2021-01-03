package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Baek_1916 {
	static int INF = Integer.MAX_VALUE;
	static int n, m;
	static ArrayList<Node> [] list;
	static boolean [] visited;
	static int dis[];
	static int start, end;  //출발지와 도착지
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		list = new ArrayList[n+1];
		visited = new boolean [n+1];
		dis= new int[n+1];
		clear();
		String [] temp;
		for(int i=0;i<m;i++) {
			temp = br.readLine().split(" "); 
			int s = Integer.parseInt(temp[0]);
			int e = Integer.parseInt(temp[1]);
			int cost = Integer.parseInt(temp[2]);
			list[s].add(new Node(e,cost));
		}
		temp = br.readLine().split(" "); 
		start = Integer.parseInt(temp[0]);
		end = Integer.parseInt(temp[1]);
		dijkstra();
		for(int data: dis) {
			//System.out.print(data+" ");
		}
		System.out.println(dis[end]);
	}
	
	private static void clear() {
		// TODO Auto-generated method stub
		for(int i=0;i<n+1;i++) {
			dis[i]=INF;
			list[i]= new ArrayList<>();
		}
	}

	private static void dijkstra() {
		PriorityQueue <Node> pq = new PriorityQueue<>();
		dis[start]=0;
		pq.add(new Node(start,dis[start]));
		
		while(!pq.isEmpty()) {
			Node n = pq.poll();
			if(visited[n.index]) continue;
			visited[n.index]=true;
			ArrayList <Node> getNode = list[n.index];
			for(int i=0;i<getNode.size();i++) {
				
				int current = getNode.get(i).index;
				int currentDis = n.cost+ getNode.get(i).cost;
				//System.out.println("들어와?"+current+" "+currentDis);
				if(currentDis<dis[current]) {
					//값 갱신
					dis[current]=currentDis;
					pq.add(new Node(current, currentDis));
				}
			}
		}
	}

	private static class Node implements Comparable<Node>{
		int index, cost;
		Node(int index, int cost){
			this.index=index;
			this.cost=cost;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.cost-o.cost;
		}
	} 
}
