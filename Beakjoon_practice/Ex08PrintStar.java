package Day03;

import java.util.Scanner;

//별을 찍어봅시다.
public class Ex08PrintStar {

    public static void main(String[] args) {
        // 전통적으로 for문과 친해지기 위해서 해보는 별찍기를 해봅시다.
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        // 별찍기 1번
        // *
        // **
        // ***
        // ****
        // *****
        System.out.println("============1번============");
        for (int height = 1; height <= N; height++) {

            for (int j = 0; j < (height); j++) {
                System.out.print("*");
            }

            System.out.println();
        }
        System.out.println("============2번============");
        for (int height = 1; height <= N; height++) {
            for (int width = 0; width <= N-height; width++) {
               System.out.print("*");
            }
            System.out.println();
        }
        System.out.println("============3번============");
        for (int height = 1; height <= N; height++) {
            String stars = "";
            // 3번처럼 왼쪽에 공잭이 있는 것은 whidth for문이 2개 나온다
            // 공백을 넣는 for문하나
            // 별을 출력하는 for문하나
            for (int width = 1; width <= N - height; width++) {
                
                System.out.print(" ");
            }
            for (int width = 1; width <= height; width++) {
                stars += "*";
                System.out.print("*");
            }
            System.out.println();

        }
        System.out.println("============4번============");
        for (int height = 1; height <= N; height++) {
            
            for (int width = 0; width <= height-1; width++) {

                System.out.print(" ");
            }
            for (int width = 0; width <= N-height; width++) {

                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println("============5번============");
        for(int height=1;height<=N;height++) {
            for(int width=1;width<=N-height;width++) {
                System.out.print(" ");
            }
            for(int width=1; width<=2*height-1;width++) {
                System.out.print("*");
            }
            System.out.println();
        }
        
        System.out.println("============6번============");
        for(int height=1;height<=N;height++) {
            for(int width=1;width<=height-1;width++) {
                System.out.print(" ");
            }
            for(int width=1; width<=2*(N-height+1)-1;width++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println("============7번============");
        for(int height=1;height<=N*2-1;height++) {
            if(height>5) {
                for(int width = 0;width<2*N-height;width++) {
                    System.out.print("*");
                }
                
            } else {
                for(int width=1;width<=height;width++) {
                System.out.print("*");
            }
            }
            
           
            System.out.println();
        }
        System.out.println("============8번============");
        for(int height=1;height<=N*2-1;height++) {
            if(height>5) {  //height=6
                
                
                for(int width = 0; width<=height-N;width++ ) {
                    System.out.print(" ");
                }
                    
                for(int width = 0;width<2*N-height;width++) {
                    System.out.print("*");
                }   
            } else {
                for(int width =0;width<=N-height;width++ ) {
                   System.out.print(" ");
               }
                 for(int width = 1;width<height+1;width++) {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
        System.out.println("============9번============");
        for(int height=1;height<=N*2-1;height++) {
            if(height>5) {
                for(int width=1;width<=height-N;width++) {
                    System.out.print(" ");
                }
                for(int width=1; width<=2*(2*N-height)-1;width++) {
                    System.out.print("*");
                }
            } else {
                for(int width=1;width<=N-height;width++) {
                System.out.print(" ");
            }
            for(int width=1; width<=2*height-1;width++) {
                System.out.print("*");
            }
            
            }
           System.out.println();
            
        }
        System.out.println("============10번============");
        for (int i=0;i<2*N-1;i++) {   //첫번쨰 줄
                System.out.print("*");
            }
        System.out.println();
        for(int height=1;height<=2*(N-1);height++) {
            if(height>4) {
                for(int width =1;width<=height-N+1;width++) {
                    System.out.print("*");
                }
                for(int width =1; width<=2*(2*N-height)-3;width++) {
                    System.out.print(" ");
                }
                for(int width =1;width<=height-N+1;width++) {
                    System.out.print("*");
                }
            } else {
                for(int width=1; width<=N-height;width++) {
                    System.out.print("*");
                }
                for(int width=1;width<=2*height-1;width++) {
                    System.out.print(" ");
                }
                for(int width=1; width<=N-height;width++) {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
        for (int i=0;i<2*N-1;i++) { //마지막 줄
            System.out.print("*");
        }
        
        //chojeyungbit@gmail.com
        sc.close();
    }

}
