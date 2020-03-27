package Day03;

import java.util.Scanner;

//사용자가 값을 2개 입력하면 
//그 숫자들 사이의 합
//만약 1, 5라고 입력하면 1+2+3+4+5

public class Ex05for {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("첫번째 숫자를 입력해주세요: ");
        int startNumber = sc.nextInt();
        System.out.println("두번째 숫자를 입력해주세요: ");
        int endNumber = sc.nextInt();
        int sum = 0;

        // a만약 사용자가 첫번쨰 숫자를 더 큰 값을 넣게 되면 for문 자체가실행되지 않는다.
        // 따라서 첫번째 숫자와 두번째 숫자를 비교해서 만약에 첫번째 숫자가 더크면 두개의 순서를 바꿔준다.
        if (endNumber < startNumber) {
            for (int i = endNumber; i <= startNumber; i++) {
                sum += i;
            }
        } else {
            for (int i = startNumber; i <= endNumber; i++) {
                sum += i;
            }
        }
        System.out.println(sum);

        // 줄단위 옮길 때 (alt+화살표 키)

    }

}
