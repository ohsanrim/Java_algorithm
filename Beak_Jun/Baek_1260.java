package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Baek_1260 {
	static int n,m,v;
	static boolean [] visited;
	static String answer="";
	//static int [][] map;
	static ArrayList <Point> point = new ArrayList<Point>();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);
		v = Integer.parseInt(temp[2]);
		visited = new boolean[n];
		//map = new int[n][n];
		for(int i=0;i<m;i++) {
			temp = br.readLine().split(" ");
			int x = Integer.parseInt(temp[0]);
			int y = Integer.parseInt(temp[1]);
			
			point.add(new Point(x,y));
			point.add(new Point(y,x));
		}
		Collections.sort(point);
		visited[v-1]=true;
		answer+=v+" ";
		dfs(v,0);
		System.out.println(answer);
		bfs(v);
	}
	private static void bfs(int choice) {
		// TODO Auto-generated method stub
		visited = new boolean[n];
		answer=v+" ";
		visited[v-1]=true;
		Queue <Integer> q = new LinkedList<Integer>();
		q.add(choice);
		while(!q.isEmpty()) {
			int c = q.poll();
			for(int i=0;i<point.size();i++) {
				Point p =point.get(i);
				if(p.x>c) break;
				if(p.x==c && !visited[p.y-1]) {
					visited[p.y-1]=true;
					q.add(p.y);
					answer+=p.y+" ";
				}
			}
		}
		System.out.println(answer);
	}
	private static void dfs(int choice, int level) {
		for(int i=0;i<point.size();i++) {
			Point p = point.get(i);
			if(p.x==choice && !visited[p.y-1]) {
				//System.out.println(choice+" "+p.x+" "+p.y);
				visited[p.y-1]=true;
				answer+=p.y+" ";
				dfs(p.y, level+1);
			}
			if(p.x>choice) break;
		}
	}
	private static class Point implements Comparable<Point>{
		int x,y;
		Point(int x, int y){
			this.x=x;
			this.y=y;
		}
		@Override
		public int compareTo(Point o) {
			// TODO Auto-generated method stub
			if(x==o.x) return y-o.y;
			return x-o.x;
		}
	}
}
