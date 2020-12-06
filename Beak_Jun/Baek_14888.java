package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baek_14888 {
	public static int N;
	public static int min;
	public static int max;
	public static ArrayList <Integer> input;
	public static int [] operator;
	//public static ArrayList <String> cal;
	public static int cal;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		min = 1000000000;
		max = -1000000000;
		input = new ArrayList<Integer>();
		operator = new int[4];
		String [] temp = br.readLine().split(" ");
		for(int i=0;i<N;i++) {
			input.add(Integer.parseInt(temp[i]));
		}
		//cal = new ArrayList<String>();
		//cal = 0;
		temp = br.readLine().split(" ");
		for(int i =0;i<4;i++) {
			operator[i] = Integer.parseInt(temp[i]);
		}
		dfs(0);
		System.out.println(max+"\n"+min);
	}
	public static void dfs(int level) {
		if(level==0) {cal = input.get(0);}
		if(level+1==N) {
			//계산 끝
			if (min>cal)min=cal;
			if (max<cal)max=cal;
		}  else {
			//계산중
			for(int i=0;i<4;i++) {
				if(operator[i]>0) {
					int temp = cal; //계산 전 값 저장
					operator[i]--;
					if(i==0) cal+=input.get(level+1);
					else if(i==1) cal-=input.get(level+1);
					else if(i==2) cal*=input.get(level+1);
					else if(i==3) cal/=input.get(level+1);
					dfs(level+1);
					operator[i]++;
					cal=temp;
				}
			}
			
		}
	}
	/*
	public static void dfs(int level) {
		if(level+1==N) {
			//계산 끝
			check();
		}  else {
			//계산중
			for(int i=0;i<4;i++) {
				if(operator[i]>0) {
					operator[i]--;
					if(i==0) cal.add("+");
					else if(i==1) cal.add("-");
					else if(i==2) cal.add("*");
					else if(i==3) cal.add("/");
					dfs(level+1);
					operator[i]++;
					cal.remove(cal.size()-1);
				}
			}
			
		}
	}
	
	public static void check() {
		ArrayList <Integer> number = (ArrayList<Integer>) input.clone();
		ArrayList <String> check = (ArrayList<String>) cal.clone(); 

		while(check.size()>0) {
			int temp =0;
			if(check.size()>0) {
				if(check.get(0).equals("*")) { temp=number.get(0)*number.get(1); }
				else if(check.get(0).equals("/")) { temp=number.get(0)/number.get(1); }
				else if(check.get(0).equals("+")) { temp=number.get(0)+number.get(1); }
				else if(check.get(0).equals("-")) { temp=number.get(0)-number.get(1); }
				number.remove(0); 
				number.remove(0);
				number.add(0, temp);
				check.remove(0);
			}
		}
		if (min>number.get(0))min=number.get(0);
		if (max<number.get(0))max=number.get(0);
	}
	
	public static void check1() {
		ArrayList <Integer> number = (ArrayList<Integer>) input.clone();
		ArrayList <String> check = (ArrayList<String>) cal.clone(); 
		
		while(number.size()>1) {
			int index = 0;
			int temp=0;
			if(check.contains("*")) {
				//System.out.println(check.lastIndexOf("*"));
				index = check.lastIndexOf("*");
				temp = number.get(index)*number.get(index+1);
			} else if(check.contains("/")) {
				index = check.lastIndexOf("/");
				temp = number.get(index)/number.get(index+1);
			} else if(check.contains("+")) {
				index = check.lastIndexOf("+");
				temp = number.get(index)+number.get(index+1);
			} else if(check.contains("-")){
				index = check.lastIndexOf("-");
				temp = number.get(index)-number.get(index+1);
			} 
			number.remove(index); 
			number.remove(index);
			number.add(index, temp);
			check.remove(index);
		}

		if (min>number.get(0))min=number.get(0);
		if (max<number.get(0))max=number.get(0);
	}
	*/

}
