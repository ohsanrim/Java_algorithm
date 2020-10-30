package Beak_Jun;

import java.io.IOException;
import java.util.Scanner;

public class Baek_1149 {
	public static int [][] list;
	public static int [][] sumList;
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		sc.nextLine();
		sumList =new int [num][3];
		for(int i=0;i<num;i++) {
			String []line = sc.nextLine().split(" ");
			if(i==0) {
				sumList[0][0]=Integer.parseInt(line[0]);
				sumList[0][1]=Integer.parseInt(line[1]);
				sumList[0][2]=Integer.parseInt(line[2]);
			} else {
				sumList[i][0]=Math.min(sumList[i-1][1], sumList[i-1][2])+Integer.parseInt(line[0]);
				sumList[i][1]=Math.min(sumList[i-1][0], sumList[i-1][2])+Integer.parseInt(line[1]);
				sumList[i][2]=Math.min(sumList[i-1][1], sumList[i-1][0])+Integer.parseInt(line[2]);
			}
		}
		System.out.println(Math.min(Math.min(sumList[num-1][0], sumList[num-1][1]), sumList[num-1][2]));
	}
}
