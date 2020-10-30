package Beak_Jun;

import java.util.Scanner;

public class QuizEx3 {

	public static void main(String[] args) {
		 String[] data = { 
                 "���� �� Ű���尡 �ƴ� ����?`2`final`True`if`public", 
                 "���� �� �ڹ��� �����ڰ� �ƴ� ����?`6`&`|`++`!=`/`^", 
                 "���� �� �޼����� ��ȯ���� ������ �ǹ��ϴ� Ű�����?`1`void`null`false`", 
           }; 
           
           Scanner s = new Scanner(System.in); 
           int score = 0; 

           shuffle(data); // ������ ���´�.

           for(int i=0;i < data.length;i++) { 
                 String[] tmp = data[i].split("`",3); 

                 String question = tmp[0]; 
                 String answer = tmp[1]; 
                 String[] choices = tmp[2].split("`"); 

                 answer = choices[Integer.parseInt(answer)-1]; 

                 shuffle(choices); // �������� ���´�.

                 System.out.println("["+(i+1)+"] "+question); 

                 for(int x=0;x < choices.length;x++) { 
                     System.out.println((x+1)+"."+choices[x]);

                 } 

                 System.out.println(); 
                 System.out.print("[��]"); 
                 String input = s.nextLine(); 
                 
                 if(input.equals(answer)) { 
                       score++; 
                 } 

                 System.out.println(); 
                 System.out.println(); 
           } 

           System.out.println("���䰳��/��ü���׼� :"+score+"/"+data.length); 
     } // main 

     public static void shuffle(String[] data) { 

          // �ڵ带 �ϼ��ϼ���.

          //  1. �迭data�� ������ 0���� ���ų� ������ �޼��� ��ü�� ����������.

          //  2. �������� ������ �ڹٲ۴�. �ݺ����� Math.random()���
     } // shuffle() 



	}

