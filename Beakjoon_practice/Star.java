package git_practice;  

import java.util.Scanner;

public class Star {  //재귀함수를 통한 N*N형태로 별 출력하기

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for(int i=0; i<N;i++) {
			Function(N);
			System.out.println();
		}
	}
	
	public static void Function(int N) {
		if (N==0) {
			return;
		} else {
			System.out.print("*");
			Function(N-1);
			return;		
		}
	}
}
