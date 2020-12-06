package Beak_Jun;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Baek_2164 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Queue <Integer> q = new LinkedList<>();
		for(int i=1;i<=n;i++) {
			q.add(i);
		}
		while(q.size()>1) {
			q.poll();
			int temp = q.poll();
			q.add(temp);
		}
		System.out.println(q.poll());
	}
}
