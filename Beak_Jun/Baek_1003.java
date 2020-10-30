package Beak_Jun;

import java.io.IOException;
import java.util.Scanner;

public class Baek_1003 {
	public static long[] answer;
	public static int [] count0;
	public static int [] count1;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int number=sc.nextInt();
		for(int i=0;i<number;i++) {
			int n = sc.nextInt();
			answer = new long[n+1];
			count0=new int[n+1];
			count1=new int[n+1];
			long result =Fibonacci(n);
			System.out.println(count0[n]+" "+count1[n]);
		}
	}
	public static long Fibonacci(int n) {
		
		if(answer[n]!=0) return answer[n];
		if(n==0) {
			answer[n]=0;
			count0[0]=1;
			count1[0]=0;
			return answer[n];
		} else if(n==1) {
			answer[n]=1;
			count0[1]=0;
			count1[1]=1;
			return answer[n];
		} else {
			answer[n]=Fibonacci(n-1)+Fibonacci(n-2);
			count0[n]=count0[n-1]+count0[n-2];
			count1[n]=count1[n-1]+count1[n-2];
			return answer[n];
		}
	} 
}