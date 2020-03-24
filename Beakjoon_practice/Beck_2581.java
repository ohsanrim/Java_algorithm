package git_practice;

import java.util.Scanner;

public class Beck_2581 {   //¼Ò¼ö

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int max=0;
		int [] A = new int[m-n];
		int x=0;
		for(int i=n;i<=m;i++) {
			boolean a = true;
			if(i==1) {
				a=false;
			} else if(i==2) {
				a=true;
			} else {
				for(int j =2;j<i;j++) {
				if(i%j==0) {
					a=false;
				} 
			}
			}
			if(a==true) {
				
				A[x]=i;
				max+=i;
				x++;
			}
		}
		if(max==0) {
			System.out.println(-1);
		} else {
			System.out.println(max);
			System.out.println(A[0]);
		}
	}
}
