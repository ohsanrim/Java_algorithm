package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_2630 {
	public static int [][] input;
	public static int white;
	public static int blue;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		input = new int[num][num];
		white=0;
		blue=0;
		for(int i=0;i<num;i++) {
			String temp[] = br.readLine().split(" ");
			for(int j=0;j<temp.length;j++) {
				input[i][j]=Integer.parseInt(temp[j]);
			}
		}
		cal(0,0,num);
		System.out.println(white);
		System.out.println(blue);
	}
	public static void cal(int row, int col, int size) {
		if(!check(row, col, size)) {
			//4조각으로 나눠야 할 때
			cal(row, col, size/2);
			cal(row, col+size/2, size/2);
			cal(row+size/2, col+size/2,size/2);
			cal(row+size/2,col,size/2);
		} else {
			//4조각으로 안 나누어도 될 때(전부 같은 색일 때)
			if(input[row][col]==1) blue++; else white++;
		}
	}
	public static Boolean check(int row, int col, int size) {
		int val= input[row][col];
		for(int i=row;i<row+size;i++) {
			for(int j=col;j<col+size;j++) {
				if(input[i][j]!=val) {
					return false;
				}		
			}
		}
		return true;
	}

}
