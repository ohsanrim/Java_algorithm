package Day03;

import java.util.Scanner;
import java.util.Random;

//숫자맞추기 게임


//자바에 기본적으로 랜덤한 숫자를 만들어 주는 java.util.Random 클래스로 랜덤한 숫자를 만들어서
//사용자가 그 숫자를 맞추는 게임을 만들어 봅시다.
//만약 컴퓨터가 15라는 숫자를 랜덤으로 뽑아내고
//사용자가 10을 입력하면 down이라는 글자가 뜨고 count는 점점 늘어나서
//사용자가 계속 입려하다가 숫자를 맞추면 count를 출력
public class Ex14 {
    private static final int MAX = 100;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();
        int highScore = 0;
        while (true) {
            System.out.println("=============");
            System.out.println(" 숫자 맞추기 게임!");
            System.out.println("=============");
            System.out.println("1. 새로하기 2. 최고점수보기 3. 종료");
            System.out.print("> ");
            int choice = sc.nextInt();
            if (choice == 1) {
                int computerNumber = rd.nextInt(MAX) + 1;
                // 우리가 무슨 값이 나올지 모르지만 나온 값 +1 하면 1~100까지의 숫자가 나오지 않을까?
                int score = 1;
                int userNumber = -1;
                while (userNumber != computerNumber) {
                    System.out.println("숫자를 입력하세요: (-1은 종료)");
                    userNumber = sc.nextInt();
                    if (userNumber == -1) {
                        break;
                    }
                    if (userNumber > computerNumber) {
                        System.out.println("DOWN");
                    } else if (userNumber < computerNumber) {
                        System.out.println("Up");
                    } else if (userNumber == computerNumber) {
                        System.out.println("정답입니다!");
                        System.out.println("점수: " + score);
                        if (score < highScore) {
                            highScore = score;
                        }
                    }
                    score++;
                }
            } else if (choice == 2) {
                // highScore출력
                // 단 highScore가 0일 땐 플레이기록이 없다고 메세지를 띄워준다.
                // random을 이용해서 숫자를 하나 만들어 준다
                // 근데 우리가 한계값을 정해줘야 함
                // 한계값을 정해줄 때 랜덤의 해당데이터타입 next메소드를 실행하면서 괄호아의 최대값을 적어주면
                // 0~최대값-1 까지의 값이 하나 랜덤하게 나온다.
                if (highScore == 0) {
                    System.out.println("아직 플레이 기록이 없습니다. ");
                } else {
                    System.out.println("최고점수: " + highScore);
                }

            } else if (choice == 3) {
                System.out.println("플레이 해주셔서 갑사합니다!");
                break;
            } else {
                System.out.println("잘못입력하셨습니다.다시한번 입력해주세요: ");
                choice = sc.nextInt();
            }

        }
    }

}
