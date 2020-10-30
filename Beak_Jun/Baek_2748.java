package Beak_Jun;

import java.util.Scanner;

public class Baek_2748 {
	public static long[] answer;
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		int n=sc.nextInt();
		answer = new long[n+1];
		long result =Fibonacci(n);
		System.out.println(result);
	}
	public static long Fibonacci(int n) {
		
		if(answer[n]!=0) return answer[n];
		if(n==0) {
			answer[n]=0;
			return answer[n];
		} else if(n==1) {
			answer[n]=1;
			return answer[n];
		} else {
			answer[n]=Fibonacci(n-1)+Fibonacci(n-2);
			return answer[n];
		}
	} 
}

