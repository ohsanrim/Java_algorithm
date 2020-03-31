package Beak_Jun;

import java.util.Scanner;

public class Beak_1463 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int num = 0; // 숫자 안의 3을 세는 변수
        int N = sc.nextInt();
        int result = number(N);
        System.out.println(result);
    }
//
    public static int number(int N) {
        int number = 0;  //666이 3개 이상 들어가는 숫자가 나오면 1++이 된다.
        int i=666;  //666부터증가하는 숫자
        while(true) {
            if(count6Number(i)==true) {
                number++;
            }
            if(number==N) {
                return i;
            }
            i++;
        }
    }

    public static boolean count6Number(int N) {
        // 입력 받은 숫자에 6이  들어간수의 갯수를 출력하는 메소드
        int continue6=0;
        int temp = N;  //6
        int temp1 = 0;  //6
        while (true) {
            if (temp >= 10) {
                if (temp % 10 == 6) { //현재 값이 6일때
                    if(temp1==6) {  //직전의 값역시 6이었다면
                        continue6++;  //1
                        if(continue6>=2) {
                            return true;
                        }
                    } 
                } else {
                    continue6=0;  //6이 아닐 때 continue의 값 초기화
                }
                
                temp1=temp%10;  //6
                temp=temp/10;   //6
            } else {
                if(temp==6) {
                    if(temp1==6) {
                        continue6++;
                        if(continue6>=2) {
                            return true;
                        }
                    }
                    break;
                } else {
                    break;
                }
            }
        }
        return false;
   



	}

}
