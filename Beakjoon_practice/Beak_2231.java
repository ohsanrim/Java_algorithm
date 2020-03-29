package Beak_Jun;

import java.util.Scanner;

public class Beak_2231 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		System.out.println(Function(N));
	}

	public static int Function(int N) {
		int answer = 0;
		for (int i = 1; i < N; i++) {
			int sum = 0; // 각 자릿수의 합
			if (i < 10) { // i가 1의 자리일때
				sum = i;
			} else { // i가 10의자리 이상일 때
				int num = i;
				while (true) {
					sum += num % 10;
					num = num / 10;
					if (num < 10) {
						sum += num;
						break;
					}
				}
			}
			if (i + sum == N) {
				answer = i;
				break;
			}
		}
		return answer;
	}
}
