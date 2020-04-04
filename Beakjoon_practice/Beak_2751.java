package boardProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Beak_2751 {
    public static int[] number;
    public static int[] temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        number = new int[N];
        temp = new int[number.length];
        for (int i = 0; i < number.length; i++) {
            st = new StringTokenizer(br.readLine());
            number[i] = Integer.parseInt(st.nextToken());
        }
        mergeSort(0, number.length - 1, number);
       
        for(int i=0;i<number.length;i++) {
            bw.write(number[i]+"\n");
        }
        bw.flush();
   
    }
    public static void merge(int left, int right, int[] number) {

        int mid = (left + right) / 2;
        int p = left;
        int q = mid + 1;
        int k = left;
        while (p <= mid || q <= right) {
            if (p <= mid && q <= right) {
                if (number[p] > number[q]) {
                    temp[k++] = number[q++];
                } else {
                    temp[k++] = number[p++];
                }
            } else if (p <= mid && q >= right) {
                temp[k++] = number[p++];
            } else {
                temp[k++] = number[q++];
            }
        }
        for(int i=left;i<right+1;i++) {
            number[i]=temp[i];
        }

    }

    public static void mergeSort(int left, int right, int[] number) {
        int mid;
        if (left < right) {
            mid = (left + right) / 2;
            mergeSort(left, mid, number);
            mergeSort(mid + 1, right, number);
            merge(left, right, number);
        }
    }
}