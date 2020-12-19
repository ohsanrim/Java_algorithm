package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baek_3986 {
	public static int count;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		count = 0;
		for (int i = 0; i < N; i++) { 
			Queue<Object> q = new LinkedList<Object>();
			String[] temp= br.readLine().split("");
			int length=temp.length;
			for(int j=0;j<length;j++) {
				q.add(temp[j]);
			}
			while(length>0) {
				length--;
				if(q.size()==0) break;
				String check = (String)q.poll();
				String check1 = (String)q.peek();
				if(!check.equals(check1)) {
					q.add(check); 
				} else {
					q.poll();
					length=q.size();
				}
			} 
			if(q.size()==0) { count++; }
		} 
		System.out.println(count);
	}
}
