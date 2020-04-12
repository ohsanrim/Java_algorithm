package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek_1018 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String[][] board = new String[N][M];
        int count = 0; /// 수정해야할 값의 개수
        int min = 2500;

        for (int i = 0; i < N; i++) { // 사용자의 입력값을 배열board에 넣어줌
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = Character.toString(line.charAt(j));
            }
        }
            for (int i = 0; i <= N - 8; i++) { // 시작점을 구하기
                for (int j = 0; j <= M - 8; j++) {
                    count = count(board, i, j); // count함수에 넣어서 값 출력
                    if (min > count) {
                        min = count;
                    }
                }
            }
        System.out.println(min);
    }
    public static int count(String[][] board, int a, int b) { // 입력한 배열에 따른 바꿔야할 개수 출력함수
        int count = 0;
        for (int i = a; i < a + 8; i++) {
            for (int j = b; j < b + 8; j++) {
                // 첫 시작이 B일 때 자리마다 색 지정
                if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
                    if (board[i][j].equals("B")) {
                        count++;
                    }
                } else {
                    if (board[i][j].equals("W")) {
                        count++;
                    }
                }
            }
        }
        if (64 - count < count) {
            return 64 - count;
        }
        return count;
    }
}