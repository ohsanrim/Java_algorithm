package Beak_Jun;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Beak_10989 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] number = new int[N];

        int max = 0;
        for (int i = 0; i < N; i++) {
            number[i] = Integer.parseInt(br.readLine()); // 배열에 입력
            if (number[i] > max) {
                max = number[i]; // 최대값 구하는 if 식
            }
        }
        int[] temp = new int[max + 1];
        for (int i = 0; i < number.length; i++) {
            temp[number[i]]++;
        }
        int count = 0;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != 0) {
                while (temp[i] != 0) {
                    number[count] = i;
                    count++;
                    temp[i]--;
                }
            }
        }
        for (int i = 0; i < number.length; i++) {
            bw.write(number[i]+"\n");
        }
        bw.flush();
    }
}