package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Baek_2589 {
	public static String [][] input;
	public static Boolean [][] check;
	public static int wide;
	public static int height;
	public static int [][] move = {{1,0},{-1,0},{0,1},{0,-1}};  //우좌상하
	public static int max;
	public static int cal;
	public static String start;
	public static String Root;
	public static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String temp [] = br.readLine().split(" ");
		height = Integer.parseInt(temp[0]);  //5
		wide = Integer.parseInt(temp[1]);  //7
		input = new String[height][wide];
		check = new Boolean[height][wide];
		//값 넣어주기
		for(int i=0;i<height;i++) {
			System.out.println(temp.length);
			temp = br.readLine().split("");
			
			System.out.println(temp.length);
			for(int j=0;j<wide;j++) {
				input[i][j]=temp[j];
				if(temp[j].equals("W")) check[i][j]=false;
				else check[i][j]=true;
			}
		}
		for(int i=0;i<height;i++) {
			for(int j=0;j<wide;j++) {
				if(input[i][j].equals("L")) {
					check[i][j] = false;
					cal=0;
					start=j+"/"+i;
					dfs(j,i);
					check[i][j] = true;
				};
			}
		}
		temp = Root.split("/");
		cal=0;
		answer=1000000;
		check[Integer.parseInt(temp[1])][Integer.parseInt(temp[0])]=false;
		dfs(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]),Integer.parseInt(temp[3]));
		System.out.println(answer);
	}
	
	private static void dfs(int x, int y) {
		Boolean endCheck=true;
		for(int i=0;i<4;i++) {
			if(checkRoad(x+move[i][0],y+move[i][1])) {
				check[y+move[i][1]][x+move[i][0]]=false;
				cal++;
				dfs(x+move[i][0], y+move[i][1]);
				check[y+move[i][1]][x+move[i][0]]=true;
				cal--;
				endCheck=false;
			}
		}
		if(endCheck) {
			if(max<=cal) {
				max=cal;
				Root=start+"/"+x+"/"+y;
			} 
		}
	}
	private static void dfs(int x, int y, int endX, int endY) {
		System.out.println("emfdjdhsl?");
		Boolean endCheck=true;
		for(int i=0;i<4;i++) {
			if(checkRoad(x+move[i][0],y+move[i][1])) {
				check[y+move[i][1]][x+move[i][0]]=false;
				cal++;
				dfs(x+move[i][0], y+move[i][1],endX, endY);
				check[y+move[i][1]][x+move[i][0]]=true;
				cal--;
				endCheck=false;
			}
		}
		if(endCheck) {
			if(x==endX&&y==endY) {
				answer=Math.min(answer,cal);
			}
		}
	}
	private static boolean checkRoad(int i, int y) {
		if(i<0||i>wide-1||y<0||y>height-1) return false;  
		if(check[y][i]) return true;
		return false;
	}
}
