package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Baek_10773 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int i=0;i<num;i++) {
			int input = Integer.parseInt(br.readLine());
			if(input==0) {
				stack.pop();
			} else {
				stack.add(input);
			}
		}
		int answer =0;
		while(!stack.empty()) {
			answer+=stack.pop();
		}
		System.out.println(answer);
	}
}
