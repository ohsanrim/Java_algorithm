package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baek_14501 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList <int []>list = new ArrayList<int []>();
		for(int i=0;i<N;i++) {
			String temp[] = br.readLine().split(" ");
			int temp1[] = {Integer.parseInt(temp[0]),Integer.parseInt(temp[1])};
			list.add(temp1);
		}
		int [] dp = new int[N];
		int [] checkday = new int[N];
		for(int i=0;i<N;i++) {
			//일 수마다 최대값 계산
			int t=list.get(i)[0];
			int p = list.get(i)[1];
			for(int j=0;j<i;j++) {
				if(checkday[j]+j<=i) {
					//일할 수 있는 날 계산
					if(dp[j]+p> dp[i]) {
						dp[i]=dp[j]+p;
						checkday[i]=t;
					}
				}
			}
			if(dp[i]==0) {
				checkday[i]=t;
				dp[i]=p;
			}
		}
		int answer=0;
		for(int i=0;i<N;i++) {
			if(!(i+checkday[i]>N)) {
				answer= Math.max(answer,dp[i] );
			}
		}
		System.out.println(answer);
	}
}
