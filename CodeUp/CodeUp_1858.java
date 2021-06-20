package CodeUp;

public class CodeUp_1858 {

    public static void main(String[] args) {
        int n = 7;  //파스칼의 삼각형을 수현할 줄 개수를 입력하기
        Pascal(n);
    }

    public static int[] Pascal(int n) {  //파스칼의 삼각형 출력하는 메소드
        int[] arr = new int[n];
        if (n == 1) {  //맨 첫줄
            arr[0] = 1;
            System.out.println(arr[0]);
            return arr;
        } else if (n == 2) { // 두번째 줄
            arr[0] = 1;
            arr[1] = 1;
            Pascal(n - 1);
        } else { // 그 외 나머지 줄의 값
            arr[0] = 1;
            arr[n - 1] = 1;
            int[] temp = new int[n - 1];
            temp = Pascal(n - 1); // 재귀
            for (int i = 1; i < n - 1; i++) {
                arr[i] = temp[i - 1] + temp[i];
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
        return arr;
    }
}


