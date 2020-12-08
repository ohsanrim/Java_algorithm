package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Baek_1931 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int [][] arr = new int [N][2];
		for(int i=0;i<N;i++) {
			String [] temp = br.readLine().split(" ");
			arr[i][0]= Integer.parseInt(temp[0]);
			arr[i][1] = Integer.parseInt(temp[1]);
		}
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1]==o2[1]) {
					return o1[0]-o2[0];
				} else {
					return o1[1]-o2[1];
				}
				
			}
		});
		int count=0;
		int end=0;
		for(int[] data :arr) {
			if(data[0]>=end) {
				end=data[1];
				count++;
			}
		}
		System.out.println(count);
	}
}
