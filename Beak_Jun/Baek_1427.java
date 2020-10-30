package Beak_Jun;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Baek_1427 {
	public static void main(String[] args) throws IOException {
		ArrayList<Integer> li = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		while (true) {
			li.add(N % 10);
			N = N / 10;
			if (N < 1) {
				break;
			}
		}
		Collections.sort(li);
		String answer = "";
		for (int i = li.size() - 1; i >= 0; i--) {
			answer += li.get(i);
		}
		System.out.println(answer);
	}
}
