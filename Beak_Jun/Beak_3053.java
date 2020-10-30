package Beak_Jun;

import java.util.Scanner;

public class Beak_3053 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        double R = sc.nextInt();  //입력받은 반지름
        double R2=R*R;
        double PI= 3.14159265358979;
        double surround = R2*PI;
        
        System.out.printf("%.6f\n",surround);
        System.out.printf("%.6f",2*(R2));
	}

}
