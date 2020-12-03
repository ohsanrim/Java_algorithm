package Programmers;

import java.util.ArrayList;
import java.util.HashMap;

public class Programmers_42839 {
	public static String number;
	public static String[] temp;
	public static Boolean[] check;
	public static ArrayList<String> makeNum;
	public static int countNum;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		number = "17";
		temp = number.split("");
		makeNum = new ArrayList<String> ();
		check = new Boolean[temp.length];
		countNum = 0;
		cal(0);
		System.out.println(countNum);
	}

	public static void cal(int level) {
		if (level < temp.length) {
			for (int i = 0; i < temp.length; i++) {
				//System.out.println("실행" + level);
				if(level==0) {
					for(int j=0;j<check.length;j++) {
						check[j]=false;
					}
				}
				if(!check[i]) {
					makeNum.add(temp[i]);
					count();
					check[i] = true;
					if (!(level + 1 == temp.length)) {
						cal(level+1);
					}
					check[i]=false;
					if(!(makeNum.isEmpty())) makeNum.remove(level);
				}
			}
		}
	}
	
	public static void count() {
		String temp = "";
		for (String data : makeNum) {
			temp+=data;
		}
		int number = Integer.parseInt(temp);
		if(check(number)) {
			countNum++;
		}
	}
	//소수 감별
	public static Boolean check(int number) {
		if(number==1) return false;
		for(int i=2;i<number/2+1;i++) {
			if(number%i==0) {
				return false;
			}
		}
		return true;
	}

}
