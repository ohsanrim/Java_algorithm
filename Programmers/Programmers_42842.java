package Programmers;

public class Programmers_42842 {

	public static void main(String[] args) {
		int brown = 24;
		int red = 24;
		int whole = red + brown;
		int[] answer = new int[2];
		for (int i = 3; i < brown - 2; i++) {
			if (whole % i == 0) {
				if(red==(i-2)*(whole/i-2)) {
					answer[1]=i;
					answer[0]=whole/i;
					break;
				}
			}
		}
		System.out.println(answer[0] + " " + answer[1]);
	}
}
