package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Baek_5052 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());
			Boolean answer = true;
			 String [] phoneNum= new String[n];
			for(int j=0;j<n;j++) {
				phoneNum[j]=br.readLine();
			}
			Arrays.sort(phoneNum);
			System.out.println(solution(phoneNum));
		}
	}

	private static String solution(String[] phoneNum) {
		for(int i=0;i<phoneNum.length-1;i++) {
				if(phoneNum[i].length()<phoneNum[i+1].length()){
					if(phoneNum[i+1].startsWith(phoneNum[i])) {
						return "NO";
					}
				}
		}
		return "YES";
	}
}
/*
 * 
if(phoneNum[i].equals(phoneNum[j].substring(0,phoneNum[i].length()))) {
						return "NO";
					}
					
			Arrays.sort(phoneNum, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
			
ArrayList<String> list = new ArrayList<String>();
for (int j = 0; j < n; j++) {
				String input = br.readLine();
				Boolean check = true;
				for (int k = 0; k < list.size(); k++) {
					if (input.length() >= list.get(k).length()) {
						if (input.substring(0, list.get(k).length()).equals(list.get(k))) {
							check = false;
							break;
						}
					} else {
						if (list.get(k).substring(0, input.length()).equals(input)) {
							check = false;
							break;
						}
					}
				}
				if (check) list.add(input);
				else {
					answer = false;
					break;
				}
			}




 */
