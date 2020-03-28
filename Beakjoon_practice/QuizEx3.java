package studyfile;

import java.util.Scanner;

public class QuizEx3 {

	public static void main(String[] args) {
		 String[] data = { 
                 "다음 중 키워드가 아닌 것은?`2`final`True`if`public", 
                 "다음 중 자바의 연산자가 아닌 것은?`6`&`|`++`!=`/`^", 
                 "다음 중 메서드의 반환값이 없음을 의미하는 키워드는?`1`void`null`false`", 
           }; 
           
           Scanner s = new Scanner(System.in); 
           int score = 0; 

           shuffle(data); // 문제를 섞는다.

           for(int i=0;i < data.length;i++) { 
                 String[] tmp = data[i].split("`",3); 

                 String question = tmp[0]; 
                 String answer = tmp[1]; 
                 String[] choices = tmp[2].split("`"); 

                 answer = choices[Integer.parseInt(answer)-1]; 

                 shuffle(choices); // 선택지를 섞는다.

                 System.out.println("["+(i+1)+"] "+question); 

                 for(int x=0;x < choices.length;x++) { 
                     System.out.println((x+1)+"."+choices[x]);

                 } 

                 System.out.println(); 
                 System.out.print("[답]"); 
                 String input = s.nextLine(); 
                 
                 if(input.equals(answer)) { 
                       score++; 
                 } 

                 System.out.println(); 
                 System.out.println(); 
           } 

           System.out.println("정답개수/전체문항수 :"+score+"/"+data.length); 
     } // main 

     public static void shuffle(String[] data) { 

          // 코드를 완성하세요.

          //  1. 배열data의 개수가 0보다 같거나 적으면 메서드 전체를 빠져나간다.

          //  2. 선택지의 순서를 뒤바꾼다. 반복문과 Math.random()사용
     } // shuffle() 



	}

