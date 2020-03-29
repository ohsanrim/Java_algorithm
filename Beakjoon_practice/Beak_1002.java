package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak_1002 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		for (int i = 0; i < T; i++) { // 테스트케이스 반복
			int[] arr = new int [6];
			double lengthX=0;
			double lengthY=0;
			double lengthXY=0;  //X와 의 거리
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<6;j++) {   //배열에 입력값 넣기
				arr[j]=Integer.parseInt(st.nextToken());
			}
			lengthX=arr[0]-arr[3];
			lengthY=arr[1]-arr[4];
			
			if(lengthX<0) {  //양수 전환
				lengthX*=-1;
			}
			if(lengthY<0) {
				lengthY*=-1;
			}
			lengthXY=Math.sqrt(Math.pow(lengthX, 2)+Math.pow(lengthY, 2));
			int min = (arr[2]>arr[5])?arr[5]:arr[2];  //두 원 중 작은 원의 반지름
			int max = (arr[2]>arr[5])?arr[2]:arr[5];  //두 원 중 반지름이 큰 원의 길이
			if(lengthXY==0&&arr[2]==arr[5]) {  //원이 일치할 때
				System.out.println(-1);
			} else if(arr[2]+arr[5]>lengthXY&&lengthXY+min>max) {  
				System.out.println(2);
			} else if((arr[2]+arr[5])==lengthXY||min+lengthXY==max){
				System.out.println(1);
			} else {
				System.out.println(0);
			}
		}
	}

}
