package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak_7568 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] heavy= new int[N][N];  //앞에가 몸무게, 뒤에가 키
        for(int i=0;i<N;i++ ) {
            st= new StringTokenizer(br.readLine());
            for(int j=0;j<=1;j++) {
                heavy[j][i]=Integer.parseInt(st.nextToken()); 
            }  
        }
        for(int i=0;i<N;i++) {
            int count=1;
            for(int j=0;j<N;j++) {
                if(heavy[0][i]<heavy[0][j]&&(heavy[1][i]<heavy[1][j])){
                    count++;
                }
            }
            System.out.print(count+" ");
        }
        }
}
