package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Beak_4949 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String temp ="";
		while(true) {
			Stack <String> stack = new Stack<String>();
			temp=br.readLine();
			if(temp.equals(".")) break;
			for(int i=0;i<temp.length();i++) {
				char tempC = temp.charAt(i);
				if(tempC=='(') stack.add("(");
				else if(tempC==')') {
					if(stack.size()!=0&&stack.peek().equals("(")) {
						stack.pop();
					} else {
						stack.add(")");
					}
				} else if(tempC=='[') {
					stack.add("[");
				} else if(tempC==']'){
					if(stack.size()!=0&&stack.peek().equals("[")) {
						stack.pop();
					} else {
						stack.add("]");
					}
				}
			}
			System.out.println(stack.size()==0?"yes":"no");
		}
	}
}
