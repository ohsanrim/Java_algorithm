package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baek_15686 {
	public static ArrayList <int[]> chicken;
	public static ArrayList <int[]> home;
	public static int M;
	public static ArrayList<Integer> choice;
	public static int min;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		chicken = new ArrayList<int[]>();
		home = new ArrayList<int[]>();
		for(int i=0;i<N;i++) {
			input=br.readLine().split(" ");
			//print(input);
			for(int j=0;j<input.length;j++) {
				if(input[j].equals("1")) {
					int [] temp= {i,j};
					home.add(temp);
				} else if(input[j].equals("2")){
					int [] temp= {i,j};
					chicken.add(temp);
				}
			}
		}
		choice = new ArrayList<Integer>();
		min = 10000000;
		dfs(0,0);
		System.out.println(min);
	}

	private static void dfs(int start, int level) {
		// TODO Auto-generated method stub
		if(level==M) {
			//M개 치킨집 선택 완료
			check();
		} else {
			for(int i=start;i<chicken.size();i++) {
				choice.add(i);
				dfs(i+1,level+1);
				choice.remove(choice.size()-1);
			}
		}
		
	}
	private static void check() {
		//System.out.println(choice+" "+home);
		int chickenLong=0;
		for(int []data: home) {
			int minCheck=10000000;
			for(int index:choice) {
				int [] temp = chicken.get(index);
				int cal = Math.abs(data[0]-temp[0])+Math.abs(data[1]-temp[1]);
				//System.out.println(cal);
				minCheck=cal<minCheck?cal:minCheck;
			}
			chickenLong+=minCheck;
		}
		if(chickenLong<min) {
			//치킨거리 최소값 바꿔주기
			min=chickenLong;
		}
	}
}
