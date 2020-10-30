package Beak_Jun;

import java.util.Scanner;

public class beak_1978 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int count=0;
		for(int i=0; i<N;i++) {
			int x = sc.nextInt();
			if(decimal(x)==true) {
				count++;
			}
		}
		System.out.println(count);	
	}
	public static Boolean decimal(int n) {  //�Ҽ� Ȯ���Լ�
		boolean a = true;
		if(n==1) {
			a=false;
		}
		for(int i=2;i<n;i++) {
			if(n%i==0) {
				a= false;
			} 
		}
		return a;
	}

}
