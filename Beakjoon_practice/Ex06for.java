package Day03;

import java.util.Scanner;

//팩토리얼을 구하는 프로그램을 만들어보아라
//팩토리얼은 1부터 전부 더하는 수
public class Ex06for {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int sum = 1;
        if (N >= 15) {
            System.out.println("입력값이 너무 큽니다.");
        } else {

            for (int i = 1; i <= N; i++) {
                sum = sum * i;
            }
            System.out.println(sum);
        }

        // 15!은 int의 범위를 벗어나게 된다. 따랏서 15이상을 넣으면 경고메세지만 출력하자

    }
}
