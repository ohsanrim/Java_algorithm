package Beak_Jun;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Baek_11399 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.nextLine();
		String [] temp = sc.nextLine().split(" ");
		/*
		int [][] arr = new int [N][2];
		for(int i=0;i<temp.length;i++) {
			arr[i][0]=i;
			arr[i][1]=Integer.parseInt(temp[i]);
		}
		Arrays.sort(arr,new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1]==o2[1]) {
					return o1[0]-o2[0];
				} else return o1[1]-o2[1];
			}
		});
		int cal =0;
		int answer = 0;
		for(int[] data:arr) {
			answer+=data[1];
			cal+=answer;
		}
		System.out.println(cal);
		*/
		int []arr = new int[N];
		for(int i=0;i<N;i++) {
			arr[i]=Integer.parseInt(temp[i]);
		}
		Arrays.sort(arr);
		int cal =0;
		int answer = 0;
		for(int data:arr) {
			answer+=data;
			cal+=answer;
		}
		System.out.println(cal);
	}
}
