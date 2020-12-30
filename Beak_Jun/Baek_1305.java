package Beak_Jun;

import java.util.Scanner;

public class Baek_1305 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner (System.in);
		int L = Integer.parseInt(sc.nextLine());
		String s = sc.nextLine();
		getPi(s);
		

	}

	private static void getPi(String s) {
		int len = s.length();
		int [] pi = new int[len];
		int j=0;
		for(int i=1;i<len;i++) {
			while(j>0 && s.charAt(i)!=s.charAt(j)) {
				j=pi[j-1];
			}
			if(s.charAt(i)==s.charAt(j)) {
				if(j==len-1) {
					break;
				}else {
					pi[i]=++j;
				}
			}
		}
		System.out.println(len-j);
	}
}
