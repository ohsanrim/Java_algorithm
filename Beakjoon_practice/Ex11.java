package Day03;

import java.util.Scanner;

//사용자로부터 점수를 입력받아서 A ABCDF가 뜨는 프로그램을 작성하시오
//단!올바르지 않은 점수 입력 시에 올바른 점수를 입력할 떄 까지 입력받으세요
public class Ex11 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("점수를 입력하세요: ");
        int score = sc.nextInt();
        while (!(score >= 0 && score <= 100)) {
            System.out.print("올바른 점수를 다시한번 입력해주세요: ");
            score = sc.nextInt();
        }
        if (score >= 90) {
            System.out.println("A");
        } else if (score >= 80) {
            System.out.println("B");
        } else if (score >= 70) {
            System.out.println("C");
        } else if (score >= 60) {
            System.out.println("D");
        } else {
            System.out.println("F");
        }
    }
}
