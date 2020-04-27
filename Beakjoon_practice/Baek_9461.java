package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Baek_9461 {
    public static long[] numArray;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int num = sc.nextInt();
            numArray = new long[num + 1];
            System.out.println(count(num));
        }
    }
    public static long count(int n) {
        if (n >= 0) {
            if (numArray[n] != 0) {
                return numArray[n];
            } else {
                if (n == 0) {
                    numArray[n] = 0;
                    return numArray[n];
                } else if (n==1||n==2||n==3) {
                    numArray[n] = 1;
                    return numArray[n];
                } else if (n == 4 || n == 5) {
                    numArray[n] = 2;
                    return numArray[n];
                } else {
                    numArray[n] = count(n - 1) + count(n - 5);
                    return numArray[n];
                }
            }
        }
        return 0;
    }
}

