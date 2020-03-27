package Day03;

import java.util.Scanner;

//무한루프를 만들어서 멘를 만들어보자
public class Ex13 {

    public static void main(String[] args) {
      Scanner sc = new Scanner (System.in);
      while(true) {
          System.out.println("====================");
          System.out.println(" |비트 성적관리 프로그램|");
          System.out.println("====================");
          System.out.println("1. 입력 2. 출력 3. 종료");
          System.out.print("> ");
          int choice = sc.nextInt();
          if(choice==1) {
              //성적을 입력하는 코드를 출력한다.
          } else if(choice==2){
              //성적을 출력하는 코를 여기에 구현한다. 
          } else if(choice==3) {
              System.out.println("사용해주셔서 감사합니다.");
              break;
          }else {
              System.out.println("잘못 누르셨습니다.");
          }
          
          
          
          
      }
      
      
      
      
      sc.close();
    }

}
