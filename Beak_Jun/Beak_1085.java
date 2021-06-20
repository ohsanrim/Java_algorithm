package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beak_1085 {

    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       int x=Integer.parseInt(st.nextToken());
       int y=Integer.parseInt(st.nextToken());
       int w=Integer.parseInt(st.nextToken());
       int h=Integer.parseInt(st.nextToken());
       int a=0;
       int b=0;
       if(w/2>=x) {
           a=x;
       } else { 
           a=w-x;
       }
       if(h/2>=y) {
           b=y;
       } else { 
           b=h-y;
       }
       if(a>b) {
           System.out.println(b);
       } else {
           System.out.println(a);
       }
    }
}


