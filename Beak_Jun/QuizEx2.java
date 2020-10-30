package Beak_Jun;

import java.util.Scanner;

public class QuizEx2 {

	public static void main(String[] args) { 
        String[] data = { 
              "���� �� Ű���尡 �ƴ� ����?`2`final`True`if`public", 
              "���� �� �ڹ��� �����ڰ� �ƴ� ����?`6`&`|`++`!=`/`^", 
              "���� �� �޼����� ��ȯ���� ������ �ǹ��ϴ� Ű�����?`1`void`null`false`", 
        }; 
        
        Scanner s = new Scanner(System.in); 
        int count = 0; 

        for(int i=0;i < data.length;i++) { 
              String[] tmp = data[i].split("`",3); 

              String question = tmp[0]; 
              String answer = tmp[1]; 
              String[] choices = tmp[2].split("`"); 

              System.out.println("["+(i+1)+"] "+question); 
              
              for(int x=0;x < choices.length;x++) { 
                    System.out.print((x+1)+"."+choices[x]+"\t"); 
              } 
              System.out.println(); 
              System.out.println("[��]");
              String input =s.nextLine();
              
              if(input.equals(answer)) {
            	  count++;
              }

              System.out.println(); 
              System.out.println(); 
        } 
        

        System.out.println("���䰳��/��ü���׼�:"+count+"/"+data.length);


  } // main 
} 



