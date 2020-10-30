package Beak_Jun;

import java.util.Scanner;

public class QuizEx1 {  //������ ���������� ����

	public static void main(String[] args) {
		
		String[] data = { 
                "���� �� Ű���尡 �ƴ� ����?`2`final`True`if`public", 
                "���� �� �ڹ��� �����ڰ� �ƴ� ����?`5`&`|`++`!=`/`^", 
                "���� �� �޼����� ��ȯ���� ������ �ǹ��ϴ� Ű�����?`1`void`null`false", 
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




