package Programmers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Programmers_42746 {
	static ArrayList<Integer> list;
	static PriorityQueue<String> pq;

	public static void main(String[] args) {

		int[] numbers = { 0, 0, 0, 0, 0, 0 };
		pq = new PriorityQueue<String>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				int temp1 = Integer.parseInt((String) o1 + o2);
				int temp2 = Integer.parseInt((String) o2 + o1);
				System.out.println(temp1 + "/" + temp2);
				return temp2 - temp1;
			}
		});
		for (int data : numbers) {
			pq.offer(Integer.toString(data));
		}
		
		String answer = "";
		while (pq.size() > 0) {
			answer += pq.poll();
		}
		
		while (true) {
			if (answer.length() > 0) {
				if (answer.charAt(0) == '0') {
					answer = answer.substring(1);
				} else {
					break;
				}
			} else {
				answer="0";
				break;
			}
		}
		System.out.println(answer);
	}
}
