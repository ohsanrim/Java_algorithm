package Beak_Jun;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak_3009 {  //네번째 점

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int twoX=0;  //2개인 x값
        int twoY=0;  //2개인 y값
        int[] X=new int[3];  
        int[] Y=new int[3];
        for(int i=0;i<3;i++) {
        	 st = new StringTokenizer(br.readLine());
            X[i]=Integer.parseInt(st.nextToken());
            Y[i]=Integer.parseInt(st.nextToken());
            if(0<i) {
            	for(int j=0;j<i;j++) {
            		if(X[j]==X[i]) {
            			twoX=X[i];  //중복된 값을 변수에 담기
            		}
            	}
            	for(int j=0;j<i;j++) {
            		if(Y[j]==Y[i]) {
            			twoY=Y[i];
            		}
            	}	
            }
        }
        //좌표 구하기
        int x=0;
        int y=0;
        for(int i=0;i<3;i++) {
        	if(twoX!=X[i]) {
        		x=X[i];
        	}
        }
        for(int i=0;i<3;i++) {
        	if(twoY!=Y[i]) {
        		y=Y[i];
        	}
        }
        System.out.println(x+" "+y);
	}
}
