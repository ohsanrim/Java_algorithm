package git_practice;

import java.util.Scanner;

public class Fibonacci1 {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		int N = sc.nextInt();
		System.out.println(Function(N));
	}
	public static int Function(int n) {
		if(n<=1) {
			return n;
		} else {
			return Function(n-2)+Function(n-1);
		}
	}
}
