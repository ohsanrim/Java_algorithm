package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Baek_17503 {
	//맥주 축제 미풀이
	static HashMap <Integer, Integer> beerPre;  //맥주별 선호도
	static HashMap <Integer, Integer> beerLev;  //맥주별 도수레벨
	static int [][] beerArr;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input1 = br.readLine().split(" ");
		int N = Integer.parseInt(input1[0]);
		int M = Integer.parseInt(input1[1]);
		int K = Integer.parseInt(input1[2]);
		beerPre = new HashMap<Integer,Integer>();  
		beerLev = new HashMap<Integer,Integer>();
		
		//값 받기
		for(int i=0;i<K;i++) {
			String [] temp = br.readLine().split(" ");
			int prefer= Integer.parseInt(temp[0]);
			int level = Integer.parseInt(temp[1]);
			beerPre.put(i, prefer);
			beerLev.put(i, level);
		}
		int arrLength=0;
		for(int i=0;i<N;i++) {
			arrLength*=arrLength*(K-i);
		}
		beerArr= new int[arrLength][N];
		beerCal(N,K);
		
		
	}
	private static void beerCal(int N, int K) {
		
		
	}

}
/*
큐에 담아서 ArrayList



*/