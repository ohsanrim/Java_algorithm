package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baek_4354 {
	static ArrayList<Integer>list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String s=br.readLine();
			if(s.equals(".")) break;
			check(s);
		}
	}

	private static void check(String s) {
		int j=0;
		int len = s.length();
		int pi [] = new int[len];
		int num=0;
		for(int i=1;i<len;i++){
			while(s.charAt(i)!=s.charAt(j) && j>0) {
				j=pi[j-1];
			}
			
			if(s.charAt(i)==s.charAt(j)){
				pi[i]=++j;
			}
		}
		if (pi[len - 1] == 0 || pi[len - 1] % (len - pi[len - 1])!=0) {
			System.out.println(1);
		} else System.out.println(len/(len-pi[len-1]));
	}

}
