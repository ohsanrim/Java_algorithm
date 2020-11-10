package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Baek_1665 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> maxPq = new PriorityQueue<Integer>(Collections.reverseOrder()); //홀수일 땐 여기가 더 사이즈가 커야함
		PriorityQueue<Integer>minPq = new PriorityQueue<Integer>();  
		for(int i=0;i<N;i++) {
			int input = Integer.parseInt(br.readLine());
			if(maxPq.size()==minPq.size()) {
				//홀수번째 숫자를 입력받음
				if(maxPq.size()==0) {
					maxPq.add(input);
				} else {
					if(maxPq.peek()>=input) {
						//중강 값이 입력값보다 클 경우
						maxPq.add(input);
					} else {
						minPq.add(input);
						maxPq.add(minPq.poll());
					}
				}

			} else {
				//짝수번째 숫자 입력차례
				if(maxPq.peek()>input) {
					//중강 값이 입력값보다 클 경우
					maxPq.add(input);
					minPq.add(maxPq.poll());
				} else {
					minPq.add(input);
				}
			}
			System.out.println(maxPq.peek());
		}
	}
}
