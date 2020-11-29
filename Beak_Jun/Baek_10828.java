package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Baek_10828 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Stack stack = new Stack();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		for(int i=0;i<num;i++) {
			String input = br.readLine();
			//push
			if(input.contains("push")) {
				String temp[] = input.split(" ");
				stack.add(temp[1]);
			} else if(input.contains("pop")) {
				System.out.println(stack.empty()?-1:stack.pop());
			} else if(input.contains("size")) {
				System.out.println(stack.size());
			} else if(input.contains("empty")) {
				System.out.println(stack.empty()?1:0);
			} else {
				System.out.println(stack.empty()?-1:stack.peek());
			}			
		}
	}
}
