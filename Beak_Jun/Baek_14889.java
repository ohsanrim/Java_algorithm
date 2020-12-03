package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_14889 {
	public static int [][] skill;
	public static Boolean [] check;
	public static int [] startTeam;
	public static int num;
	public static int min;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		num = Integer.parseInt(br.readLine());
		skill = new int[num][num];
		startTeam = new int[num/2];
		check = new Boolean[num+1];
		min=100000;
		//값 넣기
		for(int i=0;i<num;i++) {
			String temp[] = br.readLine().split(" ");
			for(int j=0;j<num;j++) {
				skill[i][j]=Integer.parseInt(temp[j]);
			}
		}
		//팀 정하기
		selectStart(1,0);
		System.out.println(min);
	}
	public static void selectStart(int start, int level) {
		if(level==0) {
			for(int i=0;i<check.length;i++) {
				check[i]=false;
			}
		}
		if(level<=num/2-1) {
			for(int i=start;i<=num;i++) {
				if(!check[i]) {
					startTeam[level]=i;
					check[i]=true;
					selectStart(i,level+1);
					check[i]=false;
					startTeam[level]=0;
				}
			}
		} else {
			checkVal();
		}
	}
	public static void checkVal() {
		int cal=0;
		for(int i=1;i<=num;i++) {
			for(int j=i+1;j<=num;j++) {
				if(check[i]&&check[j]) {
					cal+=skill[i-1][j-1];
					cal+=skill[j-1][i-1];
				} else if(!check[i]&&!check[j]) {
					cal-=skill[i-1][j-1];
					cal-=skill[j-1][i-1];
				}
			}
		}
		cal=Math.abs(cal);
		min = cal<min?cal:min;
	}
}
