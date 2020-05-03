package Beak_Jun;
import java.util.Scanner;

public class Baek_1904 {
	public static int [] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		int n = sc.nextInt();
		arr= new int[n+1];
		int answer = tileCount(n);
		System.out.println(answer);

	}
	public static int tileCount(int n) {
		if(arr[n]!=0) return arr[n];
		if(n>0) {
			if(n==1) { arr[n]=1; return 1; }
			else if(n==2) { arr[n]=2; return 2; }
			else {
				arr[n]=(tileCount(n-1)+tileCount(n-2));
				return arr[n];
			}
		}
		return 0;
	}
}

