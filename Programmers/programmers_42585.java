package Programmers;

public class programmers_42585 {

	public static void main(String[] args) {
		String arrangement="()(((()())(())()))(())";
		int stick = 0;
		int count=0;
		for(int i=0;i<arrangement.length()-1;i++) {
			if(arrangement.substring(i, i+2).equals("()")) {
				i++;
				count+=stick;
			} else if(arrangement.charAt(i)=='(') {
				count++;
				stick++;
			} else if(arrangement.charAt(i)==')') {
				stick--;
			}
		}
		System.out.println(count);

	}

}
