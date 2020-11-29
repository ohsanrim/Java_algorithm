package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_9012 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		for(int i=0;i<num;i++) {
			String input = br.readLine();
			int count=0;
			Boolean check = true;
			for(int j=0;j<input.length();j++) {
				if(input.charAt(j)==')') {
					if(count==0) {
						check=false;
						break;
					} else {
						count--;
					}
				} else {
					count++;
				}
			}
			if(check&&count==0) System.out.println("YES");
			else System.out.println("NO");
		}
	}
}
