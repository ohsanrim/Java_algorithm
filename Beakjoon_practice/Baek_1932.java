package Beak_Jun;

import java.io.IOException;
import java.util.Scanner;
public class Baek_1932 {
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		sc.nextLine();
		int [][] list = new int[num][num];
		for(int i=0;i<num;i++) {
			String[] line = sc.nextLine().split(" ");
			if(line.length==1) {
				list[0][0]=Integer.parseInt(line[0]);
			} else {
				for(int j=0;j<=i;j++) {
					if(j==0) {
						list[i][j]=list[i-1][0]+Integer.parseInt(line[j]);
					} else if(j==i) {
						list[i][j]=list[i-1][i-1]+Integer.parseInt(line[j]);
					} else {
						list[i][j]=Math.max(list[i-1][j-1], list[i-1][j])+Integer.parseInt(line[j]);
					}
				}
			}
		}
		int max =0;
		for(int i=0;i<num;i++) {
			if(list[num-1][i]>max) max=list[num-1][i];
		}
		System.out.println(max);
	}
}
