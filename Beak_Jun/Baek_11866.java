package Beak_Jun;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Baek_11866 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner (System.in);
		String [] input = sc.nextLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);
		Queue<Integer> q = new LinkedList<>();
		String answer = "<";
		//큐에 값 넣기
		for(int i=1;i<=N;i++) {
			q.add(i);
		}
		
		while(q.size()>0) {
			if(q.size()!=N)answer+=", ";
			for(int i=0;i<K-1;i++) {
				int temp = q.poll();
				q.add(temp);
			}
			answer+=q.poll();
		}
		answer+=">";
		System.out.println(answer);
	}

}
