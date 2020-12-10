package Beak_Jun;

import java.util.Scanner;

public class Baek_1463 {
	public static int N;
	public static int [] check;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		check = new int[N+1];
		for(int i=0;i<=N;i++) {
			check[i]=-1;
		}
		check[1]=1;
		if(N>=2) check[2]=1;
		if(N>=3) check[3]=1;
		dp(N);
		System.out.println(check[N]);
	}
	private static int dp(int i) {
		// TODO Auto-generated method stub
		if(check[i]!=-1) return check[i];
		else {
			//3가지 경우의 수 check
			int temp=100000;
			if(i%3==0) {
				temp = Math.min(temp, 1+dp(i/3));
			}
			if(i%2==0) {
				temp = Math.min(temp, 1+dp(i/2));
			}
			temp = Math.min(temp, 1+dp(i-1));
			check[i]=temp;
			return check[i];
		}
	}
}
