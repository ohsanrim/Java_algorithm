package Day03;

import java.util.Scanner;

//반복문의 둘째 while
//for loop의 경우에는 좀 우리가 횟수를 명확하게 몇번 돌아갈 지 볼 수 있어ㅗㅆ다.
//그에 반해서 while은 조건식을 적어주고 그 조건식이 true면 실행! false 면 종료

public class Ex10while {

    public static void main(String[] args) {
        int count =0;
        while(count<4) {
            System.out.println(count+ "회째 반복중");
            count++;
        }
        System.out.println("while종료");
        //사실while의 가장 큰 조건은 우리가 숫자와 상관없이 실행이 가능하다는 것이다. 
        //ㅖ를 들어보자
        //사용자가 어떤 값을 입력하고
        //다시 입력을 받은 값이 전에 입력한 값과 다르면?
        //같은 값이 들어올 때 까지 입력을 하게 만드는 코드를 작성할 때에는 //
        //for문으론 가능하지만 while이 적합
        Scanner sc = new Scanner (System.in);
        System.out.println("비밀번호를 입력해주세요: ");
        String storedPassword= sc.nextLine();
        System.out.println("다시한번 비밀번호를 입력해주세요: ");
        String triedPassword = sc.nextLine();
        while(!storedPassword.equals(triedPassword)) {
            System.out.println("잘못입력하셨습니다.");
            System.out.println("다시한번 비밀번호를 입력해주세요: ");
            triedPassword  = sc.nextLine();
        }
        System.out.println("회원 등록 성공!");
        
        sc.close();
    }

}
