package git_practice;

import java.util.Scanner;

public class Beak_4948 {   //베르트랑 공준

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			int n = sc.nextInt();
			if(n==0) {
				break;
			}
			int count=0;
			boolean [] A = new boolean[2*n+1];
			for(int i=1;i<=2*n;i++) {
				if(i==1) {
					A[i]=true;
				} else if(A[i]==false){
					for(int j=2;i*j<=2*n;j++) {
						A[i*j]=true;	
					}
				}
			}
			for(int i=n+1;i<=2*n;i++) {
				if(A[i]==false) {
					count++;
				}
			}
			System.out.println(count);
		}
	}
}
