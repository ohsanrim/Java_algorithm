package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Baek_1302 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		//HashMap<String, Integer> map = new HashMap<String, Integer>();
		ArrayList <String []> map = new ArrayList<String[]>();
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			Boolean check = false;
			for(int j=0;j<map.size();j++) {
				if(map.get(j)[0].equals(input)) {
					String [] temp = {input, Integer.toString(Integer.parseInt(map.get(j)[1])+1)};
					map.remove(j);
					map.add(temp);
					check=true;
					break;
				}
			}
			if(!check) map.add(new String []{input, "1"});
		}
		Collections.sort(map, new Comparator<String []>() {
			public int compare(String[] o1, String[] o2) {
				if(o1[1].equals(o2[1])) return o1[0].compareTo(o2[0]);
				else return Integer.parseInt(o2[1])-Integer.parseInt(o1[1]);
			};
		});
		System.out.println(map.get(0)[0]);
	}

}
