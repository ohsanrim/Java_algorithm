package Beak_Jun;  

import java.util.Scanner;

public class Star {  //����Լ��� ���� N*N���·� �� ����ϱ�

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
