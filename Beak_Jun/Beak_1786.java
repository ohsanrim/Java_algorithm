package Beak_Jun;

import java.util.ArrayList;
import java.util.Scanner;

public class Beak_1786 {
	static ArrayList <Integer> list = new ArrayList<Integer>(); 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		String p=sc.nextLine();
		KMP(s,p);
		System.out.println(list.size());
		for(int data: list) {
			System.out.print(data+" ");
		}
	}

	private static void KMP(String s, String p) {
		// TODO Auto-generated method stub
		int n = s.length();
		int m = p.length();
		int [] pi = getPi(p);
		int j=0;

		for(int i=0;i<n;i++) {
			while(j>0 && s.charAt(i)!=p.charAt(j)) {
				j=pi[j-1];
			}
			if(s.charAt(i)==p.charAt(j)) {
				if(j==m-1) {
					//패턴과 똑같은 문자열을 찾았을 경우
					list.add(i-m+2);
					j=pi[j];
				} else j++;
			}
		}
	}

	private static int[] getPi(String p) {
		int m = p.length();
		int [] pi = new int[m];
		int j=0;
		for(int i=1;i<m;i++) {
			while(j>0 && p.charAt(i)!=p.charAt(j)) {
				j=pi[j-1];
			}
			if(p.charAt(i)==p.charAt(j)) {
				//문자열이 같을 경우
				pi[i]= ++j;
			}
		}
		return pi;
	}
}
