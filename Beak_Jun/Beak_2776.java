package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class Beak_2776 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<t;i++) {
			int n = Integer.parseInt(br.readLine());
			String [] temp = br.readLine().split(" ");
			//ArrayList note = new ArrayList<Integer>();
			HashSet<Integer> note = new HashSet();
			for(int j=0;j<n;j++) {
				note.add(Integer.parseInt(temp[j]));
			}
			n = Integer.parseInt(br.readLine());
			temp = br.readLine().split(" ");
			for(int j=0;j<n;j++) {
				int input = Integer.parseInt(temp[j]);
				sb.append(note.contains(input)?"1":"0").append("\n");
			}
		}
		System.out.println(sb);
	}
}
