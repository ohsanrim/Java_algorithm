package Beak_Jun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

public class Baek_2696 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int testcase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<testcase;i++) {
			int num = Integer.parseInt(br.readLine());
			PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());
			PriorityQueue<Integer> minPq = new PriorityQueue<>();
			sb.append(num/2+1+"\n");
			int count=0;
			
			while(num>0) {
				String[] input = br.readLine().split(" ");
				num-=10;
				count++;

				for(int j=0;j<input.length;j++) {
					int inputNum = Integer.parseInt(input[j]);
					
					//큐에 넣어주기
					if(maxPq.size()>minPq.size()) {
						if(maxPq.peek()>inputNum) {
							maxPq.add(inputNum);
							minPq.add(maxPq.poll());
						} else {
							minPq.add(inputNum);
						}
					} else {
						if(j==0) {
							maxPq.add(inputNum);
						} else {
							if(maxPq.peek()<inputNum) {
								minPq.add(inputNum);
								maxPq.add(minPq.poll());
							} else {
								maxPq.add(inputNum);
							}
						}
						sb.append(maxPq.peek());						
						if(j<input.length-2) sb.append(" "); 
					}
				}
				if(count%2==0) sb.append("\n"); 
				else if(count%2==1&&num>0) sb.append(" "); 
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
        bw.flush();
		bw.close();
	}
}
