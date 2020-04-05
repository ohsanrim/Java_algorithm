package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Baek_2108 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> li = new ArrayList<>();
		Iterator it = li.iterator();
		int N = Integer.parseInt(br.readLine());
		double sum = 0;
		for (int i = 0; i < N; i++) {
			li.add(Integer.parseInt(br.readLine()));
			sum += li.get(i);
		}
		Collections.sort(li); // 오름차순으로 정렬
		// 산술평균 출력
		int avg = (int) (Math.round((sum / N)));
		System.out.println(avg);
		// 중앙값 출력
		int middleIndex = (li.size() > 1) ? (li.size() / 2 + 1) : li.size();
		System.out.println(li.get(middleIndex - 1));
		// 최빈값 출력
		System.out.println(countNumber(li));
		// 범위 출력
		System.out.println(li.get(li.size() - 1) - li.get(0));
	}

	public static int countNumber(ArrayList li) { // 최빈값 구하는 메소드
		int[] numbers = new int[8001];
		for (int i = 0; i < li.size(); i++) {
			int j = (int) li.get(i);
			numbers[j + 4000]++;
		}
		int max = 0;
		for (int i = 0; i < numbers.length; i++) {
			if (max < numbers[i]) {
				max = numbers[i];
			}
		}
		int count = 0;
		int frequent = 0;
		for (int i = 0; i < numbers.length; i++) {
			if (max == numbers[i]) {
				count++;
				frequent = i;
				if (count == 2) {
					break;
				}
			}
		}
		return frequent - 4000;
	}
}
