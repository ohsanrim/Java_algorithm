package Day03;
//반복문

//반복문은 조건식을 테스트해서 true가 나오면 반복, false 가 나오면 멈춤
//반복문은 2+1이있다
//for, while, forEach
//for문은 명확하게 몇 회를 반봅=ㄱ해라 라는 의미가 강하다,
//while은 조건식이 true인 동안 계속 반복된다. 
//forEach는 배욜이나 arrayList값이 똑같은 자료형이 모여있는 애들을 출력할 때 쓰면 좋다.

public class Ex04 {

    public static void main(String[] args) {
        // for문
        for (int i = 0; i < 5; i++) {
            // i의현재값=0

            System.out.println(i);
        }
        // 또한 우리가 저 안에 넣은 i는 굳이 i 라는 이름잉 아니어도 되고
        // 초기화값이나 조건식 값도 ㅇ리가 변수를 넣어줄 수있다.
        int standard = 10;
        int startingNumber = 6;
        for (int position = startingNumber; position <= standard; position++) {
            System.out.print("standard: " + standard);
            System.out.print(", startinNUmber=" + startingNumber);
            System.out.println(", position: " + position);
            //하지만 전통적으로 for문의 선언되는 변수는 i,j,k순이다,
            
            
            
            
            
            
        }
    }

}
