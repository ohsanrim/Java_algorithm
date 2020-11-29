package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Collections;
import java.util.PriorityQueue;

public class Baek_11286 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		PriorityQueue<Integer> nagative = new PriorityQueue<Integer>(Collections.reverseOrder());
		PriorityQueue<Integer> positive = new PriorityQueue<Integer>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		for(int i=0;i<num;i++) {
			int input = Integer.parseInt(br.readLine());
			int print=0;
			if(input==0) {
				if(nagative.size()>0&&positive.size()>0) {
					if((nagative.peek()*-1)>positive.peek()) {
						print=positive.poll();
					} else {
						print=nagative.poll();
					}
				} else {
					if(nagative.isEmpty()) {
						if(positive.isEmpty()) {
						} else {
							print=positive.poll();
						}
					} else {
						print=nagative.poll();
					}
				}
				System.out.println(print);
			} else {
				if(input<0) nagative.add(input);
				else positive.add(input);
			}
			HttpURLConnection conn = 
		}

	}
}
