package studyfile;

import java.util.Scanner;

public class QuizEx1 {  //간단한 다지선다형 문제

	public static void main(String[] args) {
		
		String[] data = { 
                "다음 중 키워드가 아닌 것은?`2`final`True`if`public", 
                "다음 중 자바의 연산자가 아닌 것은?`5`&`|`++`!=`/`^", 
                "다음 중 메서드의 반환값이 없음을 의미하는 키워드는?`1`void`null`false", 
          }; 
          for(int i=0;i<data.length;i++) {
        	  
        	  String[] question = data[i].split("`");
        	  System.out.println("["+(i+1)+"]"+question[0]);
        	  for(int j=2;j<question.length;j++) {
        		  System.out.print((j-1)+"."+question[j]+"   ");
        		  
        	  }
        	  System.out.println();
        	  System.out.println();
          } 
    } // main 
	
} 




