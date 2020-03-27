package Day03;

import java.util.Scanner;

//좀 더 실제로 쓸만한 스위치문을 만들어보자
//사용자가 주소ㄹ를 입력하고ㅠ 그 주소가 특정 값과 같다면 정상적으로 초리됨
//만약 그 외에 주소가 아ㅖ 다르면 404
//주소는 같지만 뒤에 오는 값이 다르면 500에러가 뜨게 스위치문을 만들어보자
public class Ex03 {

    public static void main(String[] args) {
        Scanner sc= new Scanner (System.in);
        System.out.println("이동할 주소를 입력해주세요");
        String address= sc.nextLine();
        int statuscode=0;
        //사용자가 입력한 주소가 /admit/main/1이 아니면 무조건 404가 나오게 만들어보자
        if(address.equals("/admit/main/1")){
            statuscode=200;
        } else {
            statuscode=404;
        }
        switch(statuscode) {
        case 200:
            System.out.println("정상이동");
            break;
       
        case 404:
            System.out.println("주소가 잘못되었습니다 1. 뒤로가기 2. 홈으로");
            break;
        
        
        
        
        
        
        
        }
        sc.close();
    }

}
