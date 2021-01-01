package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baek_1759 {
	static int l,c;
	static String [] input;
	static String [] password;
	static boolean [] visited;
	static int con, vo=0;  //자음, 모음 개수 cnt
	static String [] vowel = {"a","e","i","o","u"};
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] temp = br.readLine().split(" ");
		l = Integer.parseInt(temp[0]);
		c = Integer.parseInt(temp[1]);
		password =new String[l];
		visited = new boolean[c];
		input =new String[c];
		
		temp = br.readLine().split(" ");
		for(int i=0;i<c;i++) {
			input[i]=temp[i];
			boolean check = true;
			if(voCheck(input[i])) vo++;
			else  con++;
		}
		Arrays.sort(input);
		//System.out.println(con+" "+vo);
		bfs(0,0,con, vo);
	}
	private static void bfs(int level,int index, int con2, int vo2) {
		if(l==level) {
			//종료조건
			//System.out.println(vo2+" "+con2 +"체크");
			if(vo-1>=vo2 &&  con-2>=con2) {
				for(int i=0;i<l;i++) {
					System.out.print(password[i]);
				}
				System.out.println();
			}
			return;
		}
		for(int i=index;i<c;i++) {
			if(!visited[i]) {  //사용 X
				visited[i]=true;
				password[level]=input[i];
				if(voCheck(input[i])) {
					bfs(level+1,i+1,con2, vo2-1);
				}
				else {
					bfs(level+1,i+1,con2-1, vo2);
				}
				password[level]="";
				visited[i]=false;
			}
		}
	}
	private static boolean voCheck(String s) {
		for(int j=0;j<vowel.length;j++) {
			if(s.equals(vowel[j])) {
				//모음일 때
				return true;
			} 
		}
		return false;
	}

}
