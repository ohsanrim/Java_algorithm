package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baek_2665 {
	static int n;
	static int [][] map;
	static int [][] visited;
	static int min = 100000000;
	static int[][] move = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		int cntWall=0;
		for(int i=0;i<n;i++) {
			String temp [] = br.readLine().split("");
			for(int j=0;j<temp.length;j++) {
				map[i][j]=Integer.parseInt(temp[j]);
				if(map[i][j]==1) cntWall++;
			}
		}
		visited = new int [n][n];
		clear();
		bfs();
		System.out.println(min);
	}
	private static void clear() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				visited[i][j]=-1;
			}
		}
	}
	private static void bfs() {
		// TODO Auto-generated method stub
		Queue <Point>q = new LinkedList<>();
		q.add(new Point(0,0,0));
		//visited[0][0][0]=true;
		visited[0][0]=0;
		while(!q.isEmpty()) {
			Point p = q.poll();
			if(p.x==n-1 && p.y==n-1) {
				//System.out.println(p.lev+"종료!!");
				min=Math.min(min, p.lev);
			}
			//boolean check= true;
			for(int i=0;i<4;i++) {
				int x = p.x+move[i][0];
				int y = p.y+move[i][1];
				
				if(isIn(x,y) && (visited[x][y]==-1 || visited[x][y]>p.lev)) {
					if(map[x][y]==1) {
						q.add(new Point(x,y,p.lev));
						visited[x][y]=p.lev;
						//check=false;
					} else if(check(x,y,p.lev)) {
						q.add(new Point(x,y,p.lev+1));
						visited[x][y]=p.lev;
					}
				}
			}
		}
		
	}
	private static boolean check(int pX, int pY, int pLev) {
		for(int i=0;i<4;i++) {
			int x = pX+move[i][0];
			int y = pY+move[i][1];
			if(isIn(x,y) && (visited[x][y]==-1 || visited[x][y]>pLev)) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x<n && y<n;
	}
	public static class Point{
		int x, y;
		int lev;
		Point(int x, int y, int lev){
			this.x=x;
			this.y=y;
			this.lev=lev;
		}
	}

}
/*

5
11111
00001
11111
10000
11111

8
11111011
00000001
00000001
00000001
11111101
10000000
10000000
11111111

8
11000110
11000110
11000101
00000100
00000100
00000100
00000111
00000001

8
11111110
11000110
11000001
00000000
00000000
00000000
00000111
00000001


*/