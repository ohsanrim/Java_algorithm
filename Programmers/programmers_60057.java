package Programmers;

import java.util.ArrayList;  
//2020 카카오 블라인드 공채
public class programmers_60057 {

	public static void main(String[] args) {
		String s = "a";
		int [] number;
		ArrayList<String> split;
		int answer = 1000;
		int count = 0;
		String line = s;
		if(line.length()<2) answer=1;
		for (int i = 1; i < line.length(); i++) {
			count = 0;
			int index = 0;
			number = new int[line.length() / i];
			split = new ArrayList<String>();
			// 개수별로 나누어 담기
			for (int j = 0; j < ((line.length() % i) == 0 ? line.length() / i : line.length() / i + 1); j++) {
				if (j > 0 && index + i > line.length()) {
					split.add(line.substring(index, line.length()));
				} else {
					split.add(line.substring(index, index + i));
					index += i;
				}
			}
			for (int j = 1; j < split.size(); j++) {
				if (split.get(j - 1).equals(split.get(j))) {
					split.remove(j);
					number[j]++;
					j--;
				}
			}
			for (int j = 0; j < number.length; j++) {
				if (number[j] != 0) {
					number[j]+=1;
					System.out.println(number[j]);
					while(true) {
						if(number[j]>=10) {number[j]=number[j]/10; count++;}
						else {break;}
					}
					count++;
				}
			}
			int stringCount = 0;
			for (int j = 0; j < split.size(); j++) {
				stringCount += split.get(j).length();
			}
			if (answer> stringCount + count) {
				answer = stringCount + count;
			}
		}
		System.out.println(answer);
	}
}
