package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class Beak_16236 {
	static int [][] map; 
	static int n;
	static int fish;  //물고기 크기
	static int big;  //아기상어 크기
	static Boolean [][] visited;
	//static ArrayList <Point> list = new ArrayList<>();
	static int pointX, pointY;
	static int time;
	static int [][] dist;
	public static int[][] move = { { -1, 0 }, { 0, -1 } , { 1, 0 },  { 0, 1 }}; // 우좌상하
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int [n][n];
		visited = new Boolean[n][n];
		big=2;
		for(int i=0;i<n;i++) {
			String [] temp = br.readLine().split(" ");
			for(int j=0;j<n;j++) {
				map[i][j]=Integer.parseInt(temp[j]);
				if(map[i][j]==9) {
					//아기상어 좌표
					pointX=i;
					pointY=j;
					visited[i][j]=true;
				}
			}
		}
		Point o = new Point(pointX, pointY);
		while(!(o.x==-1)) {
			o = bfs(o);
			if(fish==big) {
				big++;
				fish=0;
			}
		}
		System.out.println(time);
	}
	private static Point bfs(Point o) {
		dist = new int[n][n];
		map[pointX][pointY]=0;
		clear();
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(o.x, o.y));
		int shortTime=-1;
		ArrayList <Point> sameTime = new ArrayList<Point>();
	
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int i=0;i<4;i++) {
				int x=p.x+move[i][0];
				int y = p.y+move[i][1];
				if((dist[p.x][p.y]==shortTime)) {
					q.clear();
					break;
				}
				if(x>=0&&y>=0&&x<n&&y<n) {
					if(!visited[x][y]&&map[x][y]<=big) {
						//지나갈 수 있는 길
						if(map[x][y]!=0&&!(map[x][y]==big)) {
							//길에 먹을 수 있는 물고기 있을 떄
							visited[x][y]=true;
							//System.out.println("걸린 시간: "+(dist[p.x][p.y]+1));
							//System.out.println("먹이획득!"+x+" "+y);
							if(shortTime==-1) {
								shortTime=dist[p.x][p.y]+1;
								sameTime.add(new Point(x,y));
							} else if(shortTime==dist[p.x][p.y]+1){
								//거리가 동일한경우
								sameTime.add(new Point(x,y));
							}
							//return new Point(x,y);
						} else {
							visited[x][y]=true;
							dist[x][y]=dist[p.x][p.y]+1;
							//System.out.println(x+" "+y+" "+dist[x][y]);
							q.add(new Point(x,y));
						}
					}
				}
				
			}
		}
		if(sameTime.size()==0) return new Point(-1,-1);
		else {
			Collections.sort(sameTime, new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					if(o1.x==o2.x) {
						return o1.y-o2.y;
					} else return o1.x-o2.x; 
				}
			});
			map[sameTime.get(0).x][sameTime.get(0).y]=0;
			//System.out.println("먹힌 물고기 좌표:"+sameTime.get(0).x+" "+sameTime.get(0).y);
			fish++;
			time+=shortTime;
			//System.out.println(time);
			//System.out.println("--------------------------------");
			return (Point)sameTime.get(0);
		}
	}
	
	private static void clear() {
		for(int i=0;i<visited.length;i++) {
			for(int j=0;j<visited[i].length;j++) {
				visited[i][j]=false;
			}
		}
	}
	private static class Point{
		int x;
		int y;
		Point(int x, int y){
			this.x=x;
			this.y=y;
		}
	}

}
