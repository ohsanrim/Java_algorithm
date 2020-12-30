package Beak_Jun;

import java.util.Scanner;

public class Baek_1701 {
	static int max=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		for(int i=0;i<s.length();i++) {
			getPi(s.substring(i));
		}
		System.out.println(max);
	}

	private static void getPi(String s) {
		int len = s.length();
		int j=0;
		int [] pi = new int[len];
		for(int i=1;i<len;i++) {
			while(j>0 && s.charAt(i) != s.charAt(j)) {
				j=pi[j-1];
			}
			if(s.charAt(j)==s.charAt(i)) {
				pi[i]=++j;
				max = Math.max(max, j);
			}
		}
		
	}
}
