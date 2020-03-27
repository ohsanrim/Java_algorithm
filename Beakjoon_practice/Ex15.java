package Day03;

import java.util.Scanner;

//성적관리프로그램

//사용자가 점수를 입력하면 
//가지고있다가
//출력할 때 printF를 통해서 출력한다. 
//만약 시작하자마자 출력 누르면 정보가 입력되지 않았습니다
//만약 올바르지 않은 형태의 점수가 들어오면 다시입력해주세요 
//메뉴에서 3을 고르면 프로그램 종료
public class Ex15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = "";
        int korean = -1;
        int english = -1;
        int math = -1;
        int total =0;
        double average=0;
        while (true) {
            System.out.println("===================");
            System.out.println("   성적관리 프로그램");
            System.out.println("===================");
            System.out.println("1. 입력 2. 출력 3. 종료");
            System.out.println("> ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                System.out.print("이름을 입력해주세요: ");
                name= scanner.nextLine();
                System.out.print("국어 점수를 입력해주세요: ");
                korean = scanner.nextInt();
                if (korean > 100 || korean < 0) {
                    System.out.println("잘못 입력하셨습니다");
                    System.out.print("국어 점수를 입력해주세요: ");
                    korean = scanner.nextInt();
                }
                System.out.print("영어 점수를 입력해주세요: ");
                english = scanner.nextInt();
                if (english > 100 || english < 0) {
                    System.out.println("잘못 입력하셨습니다");
                    System.out.print("영어 점수를 입력해주세요: ");
                    english = scanner.nextInt();
                }
                System.out.print("수학 점수를 입력해주세요: ");
                math = scanner.nextInt();
                if (math > 100 || math < 0) {
                    System.out.println("잘못 입력하셨습니다");
                    System.out.print("수학 점수를 입력해주세요: ");
                    math = scanner.nextInt();
                }
                total= korean+english+math;
                average = total / 3.0;
            } else if (choice == 2) {
                if (korean == -1) {
                    System.out.println("성적정보가 입력되지 않았습니다.\n\n");
                } else {
                    System.out.printf("이름: %s, 국어: %d, 수학: %d,영어: %d,총점: %d,평군:%.3f점\n\n", name,korean, english, math, total,
                            average);
                }
            } else if (choice == 3) {
                System.out.println("이용해 주셔서 갑사합니다!");
                break;
            } else {
                System.out.print("번호를 다시한번 입력해 주세요: ");
                choice = scanner.nextInt();
            }
        }
    }
}
