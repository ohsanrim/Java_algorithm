package Programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Programmers_42627 {
	static PriorityQueue<Integer> pq;
	static ArrayList <int[]>list;
	public static void main(String[] args) {
		// 디스코 컨트롤러
		int [][] jobs= {{0,3},{1,9},{2,6}};
		pq = new PriorityQueue<Integer>();
		int count=0;
		int endtime=0;
		HashMap<Integer, Integer> jobTime = new HashMap<Integer, Integer>();  //작업에 따른 시간
		boolean work=false;
		list = new ArrayList<int[]>();
		for(int i=0;i<jobs.length;i++) {
			list.add(jobs[i]);
		}
		while(true) {
			if(count==endtime) work=false;  //프로세스 종료
			if(work) {
				//작업 중일 때 큐에 대기열 넣어주기
				for(int i=0;i<list.size();i++) {
					if(count==list.get(i)[0]) {
						pq.add(i);
						jobTime.put(i, jobs[i][1]);
					}
				}
			} else {
				//작업 끝났을 때
				

			}

		}

	}

}
