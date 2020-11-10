package Programmers;

import java.util.PriorityQueue;

public class Programmers_42626 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] scoville= {1,2,3};
		int K =11;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for(int i=0;i<scoville.length;i++) {
			pq.add(scoville[i]);
		}
		int count=0;
		while(true) {
			if(pq.size()==1) {
				count=-1;
				break;
			}
			if(pq.peek()>=K) {
				break;
			} else {
				int first = pq.poll();
				int second = pq.poll();
				int newFood = first+(second*2);
				pq.add(newFood);
				count++;
			}
		}
		System.out.println(count);
	}
}
