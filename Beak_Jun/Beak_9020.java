package Beak_Jun;

import java.util.Scanner;

public class Beak_9020 {    //�������� ����

	public static void main(String[] args) {  
		Scanner sc = new Scanner(System.in);
		int T =sc.nextInt();
		for(int m=0;m<T;m++) {
			int n = sc.nextInt();
			
			boolean [] A = new boolean[n+1];
			for(int i=1;i<=n;i++) {
				if(i==1) {
					A[i]=true;
				} else if(A[i]==false){
					for(int j=2;i*j<=n;j++) {
						A[i*j]=true;	
					}
				}
			}
			//���� �� ���� �Ҽ� �� ���ϱ�
			int x=0;
			for(int i=1;i<=n/2;i++) { 
				if(A[i]==false) {
					if(A[n-i]==false) {
						x=i;
					}
				}
			}
			System.out.println(x+" "+(n-x));
		}

	}

}
