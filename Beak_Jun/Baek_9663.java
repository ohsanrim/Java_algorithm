package Beak_Jun;

import java.util.Scanner;

public class Baek_9663 {
	//public static Boolean checkLine[];
	public static int N;
	public static int col[];
	public static int answer;
	static int cnt=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		//checkLine = new Boolean[N];
		col = new int[N];
		answer=0;
		dfs(0);
		System.out.println(cnt);
	}
	public static void dfs(int level) {
		if(level==N) {
			//끝났을 경우
			cnt++;
			return;
		} else {
			//아직 계산중일 때
			for(int i=0;i<N;i++) {
				if(check(i,level)) {
					//조건에 맞을 경우
					col[level]=i;
					dfs(level+1);
				}
			}
		}
	}
	public static Boolean check(int i, int level) {
		if(level>0) {
			for(int j=0;j<level;j++) {
				//상하대각선 같은 줄인지 확인
				if(i==col[j]) return false;
				if(i+(level-j)==col[j]||i-(level-j)==col[j]) return false;
			}
			if(col[level-1]-1<=i&&col[level-1]+1>=i) {
				return false;
			}
		}
		return true;
	}
}
