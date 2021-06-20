package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baek_15684 {
	static int N = 0;  //세로선 개수
	static int M = 0;  //가로선 개수
	static int H = 0;	//가로선을 놓을 수 있는 위치의 개수 
	static boolean [][] map; 
	
	static int lineCnt = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		H = Integer.parseInt(str[2]);

		map = new boolean [N][H];
		
		for(int i=0;i<M;i++) {
			String line = br.readLine();
			if(line == null || line.equals("")) break;
			
			str = line.split(" ");
			int startNum = Integer.parseInt(str[1]) - 1;
			int linkedNum = Integer.parseInt(str[0]) - 1;
			map[startNum][linkedNum] = true;
		}
		if(check()) lineCnt = 0 ;
		else dfs(0);
		System.out.println(lineCnt==Integer.MAX_VALUE?-1:lineCnt);
	}
	
	private static void dfs(int deepth) {
		// TODO Auto-generated method stub
		if(deepth ==3) return;
		for(int i=0;i<N;i++) {
			for(int j=0;j<H;j++) {
				if(insertCheck(i, j)) {
					map[i][j] = true;
					if(check()) {
						lineCnt = lineCnt>deepth+1?deepth+1:lineCnt;
					}
					dfs(deepth+1);
					map[i][j] = false;
				}
			}
		}
	}

	private static boolean check() {
		// TODO Auto-generated method stub
		if(M == 0) return true;
		
		for(int i=0;i<N;i++) {
			int lineNum = i;
			for(int j=0;j<H;j++) {
				if(map[lineNum][j] && lineNum < N) lineNum++;
				else if ( !map[lineNum][j] && lineNum !=0 ){
					if(map[lineNum-1][j]) lineNum--;
				}
			}
			if(lineNum != i) return false;
		}
		return true;
	}

	private static boolean insertCheck(int i, int j) {
		// TODO Auto-generated method stub
		//최대로 세로줄 놓을 수 있는지 체크
		int cnt =0;
		for(int k = 0;k<H;k++) {
			if(map[i][k]) cnt ++;
		}
		if(cnt >= H) return false; 
		//세로줄 놓아도 되는지 체크
		if(map[i][j] == false && i<N-1) {
			if(i==0) {
				if(map[i+1][j] == false) return true;
			} else {
				if(map[i - 1][j] == false && map[i + 1][j] == false) return true;
			}
		}
		return false;
	}

}