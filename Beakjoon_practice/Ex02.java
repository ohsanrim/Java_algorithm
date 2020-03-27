package Day03;

import java.util.Scanner;

//그럼 의도적으로 break를 생략해서 우리가 원하는 값이 나오게 해보자
public class Ex02 {
//alt+shift+r키로 이름을 바꿈
//클래스 뿐만 아닌 변수의 이릅을 총괄하여 바꿀 때에도 사용
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("월을 입력해주세요: ");
        int month = s.nextInt();
        switch (month) {
        case 2:
            System.out.println("28일까지 입니다");
            break;
        case 4:
        case 6:
        case 9:
        case 11:
            System.out.println("30일까지 입니다.");
            break;
            default: 
                System.out.println("31일까지 입니다.");
            break;
        }
        s.close();

    }

}
