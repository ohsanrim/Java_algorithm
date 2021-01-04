package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_1937 {
	static int n;
	static int [][] map;
	static int cnt=1;
	static int [][] maxDay;
	static int [][] move ={{-1,0},{0,-1},{1,0},{0,1}};
	static int max =0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int [n][n];
		maxDay = new int [n][n];
		for(int i=0;i<n;i++) {
			String [] temp = br.readLine().split(" ");
			for(int j=0;j<n;j++) {
				map[i][j]=Integer.parseInt(temp[j]);
				maxDay[i][j]=-1;
			}
		}
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				maxDay[i][j] = dfs(i,j,map[i][j]);
				max = Math.max(maxDay[i][j], max);
			}
		}
		for(int [] arr: maxDay) {
			for(int data:arr) {
				System.out.print(data+" ");
			}
			System.out.println();
		}
		System.out.println(max);
	}
	
	private static int dfs(int px, int py, int num) {
		boolean check = true;
		for(int i=0;i<4;i++) {
			int x = px+move[i][0]; 
			int y = py+move[i][1];
			
			if(isIn(x,y)) {
				if(map[x][y]>num) {
				//판다 이동
					if(maxDay[x][y]!=-1) {
						//이미 계산
						check=false;
						maxDay[px][py]=Math.max(1+maxDay[x][y],maxDay[px][py]);
					} else {
						maxDay[px][py] = Math.max(maxDay[px][py], 1+dfs(x,y,map[x][y]));
						check=false;
					}
				}
			}
		}
		if(check) {
			maxDay[px][py]=1;
		}
		return maxDay[px][py];
	}
	private static boolean isIn(int x, int y) {
		// TODO Auto-generated method stub
		return x>=0 && y>=0 && x<n && y<n;
	}
}
/*
1
13
*/