package Beak_Jun;
import java.util.Arrays;
import java.util.Scanner;

public class Beak_2750 {

    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
         int N = sc.nextInt();
         int[] number = new int[N];
         for(int i=0;i<N;i++) {   //수 입력받기
            number[i]=sc.nextInt();
         }
         Arrays.sort(number);
         for(int i=0;i<number.length;i++) {
            System.out.println(number[i]);  
         }
    }
}
