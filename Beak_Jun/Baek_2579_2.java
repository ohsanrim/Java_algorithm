package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baek_2579_2 {
	public static int N;
	public static int[] input;
	public static int[] stairMax;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = new int[N];
		stairMax = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(br.readLine());
			stairMax[i] = -1;
		}
		stairMax[0] = input[0];
		if(N>=2) stairMax[1] = input[1]+input[0];
		if(N>=3) stairMax[2]= Math.max(input[0]+input[2], input[1]+input[2]);
		dp(N - 1);
		System.out.println(stairMax[N-1]);
	}

	private static int dp(int i) {
		if (i == 0) {
			return stairMax[0];
		} else if (i == 1) {
			return stairMax[1];
		} else if(i==2){
			return stairMax[2];
		} else {
			if (stairMax[i] != -1)
				return stairMax[i];
			else {
				int temp = input[i] + dp(i - 2);
				int temp2 = input[i] + input[i - 1] + dp(i - 3);
				stairMax[i]=Math.max(temp, temp2);
				return stairMax[i];
			}
		}
	}
}
