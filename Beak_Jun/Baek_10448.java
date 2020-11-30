package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_10448 {
	public static int[] T;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for(int i=0;i<N;i++) {
			int K = Integer.parseInt(br.readLine());
			System.out.println(dfs(K)?1:0);
		}
	}
	public static Boolean dfs(int n) {
		for(int i=1;i<=n/2;i++) {
			int calI=i*(i+1)/2;
			if(calI>n) break;
			for(int j=1;j<=n/2;j++) {
				int calJ = j*(j+1)/2;
				if(calJ>n) break;
				for(int k=1;k<=n/2;k++) {
					int calK=k*(k+1)/2;
					if(calK>n) break;
					if(calI+calJ+calK==n) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
