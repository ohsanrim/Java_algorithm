package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Baek_2309 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int [] input = new int [9];
		int sum = -100;
		for(int i=0;i<9;i++) {
			input[i] = Integer.parseInt(br.readLine());
			sum+=input[i];
		}		
		ArrayList  answer = new ArrayList<Integer>();  //일곱난쟁이
		boolean check = false;
		for(int i=0;i<9;i++) {
			if(check) break;
			for(int j=i+1;j<9;j++) {
				if(input[i]+input[j]==sum) {
					for(int k=0;k<9;k++) {
						if(k!=j&&k!=i)
						answer.add(input[k]);
						check=true;
					}
					break;
				}
			}
		}
		Collections.sort(answer);
		for(int i=0;i<7;i++) {
			System.out.println(answer.get(i));
		}
	}
}
