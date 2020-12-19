package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baek_1920 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		int [] arr = {2,3,7,8};
		  //arr 에서 7이란 값의 위치값을 찾는다는 말과 동일
		System.out.println(Arrays.binarySearch(arr,6));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int [] input = new int[N];
		String temp [] = br.readLine().split(" ");
		for(int i=0;i<temp.length;i++) {
			input[i] = Integer.parseInt(temp[i]);
		}
		Arrays.sort(input);
		int M = Integer.parseInt(br.readLine());
		String [] search = br.readLine().split(" ");
		for(int i=0;i<M;i++) {
			int searchVal = Arrays.binarySearch(input, Integer.parseInt(search[i]));
			System.out.println(searchVal>=0?1:0);
		}
		
	}
}
