package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_14500 {
	static int n, m;
	static int[][] map;
	static int[][] move = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
	static boolean [][] visited;
	static int max =0;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);
		map = new int[n][m];
		visited = new boolean[n][m];
		
		for (int i = 0; i < n; i++) {
			temp = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(temp[j]);
			}
		}
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				visited[i][j]=true;
				dfs(0, map[i][j],i,j);
				visited[i][j]=false;
			}
		}
		System.out.println(max);
	}

	private static void dfs(int level,int cal,int pointX, int pointY ) {
		// TODO Auto-generated method stub
		if(level==0) {
			//성모양 계산
			for(int i=0;i<4;i++) {
				int cal1=cal;
				boolean check= true;
				for(int j=0;j<4;j++) {
					int x= pointX+move[j][0];
					int y = pointY+move[j][1];
					if(i!=j) {
						if(isIn(x,y)) {
							cal1+=map[x][y];
						} else {
							check=false;
							break;
						}
					}
				}
				if(check) {
					//System.out.println("========성모샹 계산 끝:"+cal1);
					max=Math.max(max, cal1);
				}
			}
		}
		if(level==3) {
			//종료
			max=Math.max(max, cal);
			//System.out.println(cal+"현재 최대값");
			//print();
			return;
		}
		for(int i=0;i<4;i++) {
			int x= pointX+move[i][0];
			int y = pointY+move[i][1];
			if(isIn(x,y)&& !visited[x][y]) {
				//System.out.print(x+" "+y+"/");
				visited[x][y]=true;
				dfs(level+1, cal+map[x][y],x,y);
				visited[x][y]=false;
			}
		}
	}
/*
	private static void print() {
		// TODO Auto-generated method stub
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				System.out.print(visited[i][j]);
			}
			System.out.println();
		}
	}
*/
	private static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x<n && y<m;
	}

}
/*
4 4
1 2 1 2
2 1 2 1
1 2 1 2
2 1 2 1







*/
