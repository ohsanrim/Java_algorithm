package Day03;

//if문에서는 우리사 조건식을 통하서 true가나오면 실행하고 
//false가 나오면 살행하지않음

//Switch문에서는 변수를 하나 선택해서 그 변수가 나올 수 있는 값을 만들어서 ㄱ그 변수가 해당 케이스를 
//만족하게 되면 어떤것을 실행하게 한다
public class Ex01 {

    public static void main(String[] args) {
        int num = 2;
        // if(num>0)
        switch (num) {
        //break안쓰면 변수가 만족하는 케이스의 코드부터 브레이크가 나올 때까지 계속 
        //아래에 따라오는 것을 모두 실행한다.
        case 1:
            System.out.println("1입니다");

        case 2:
            System.out.println("2입니다");
         
        case 3: System.out.println("3입니다");
          
        // 이틀립스 자동 줄여쓰기 ctrl +shift + f
        default:
            System.out.println("그 외입니다.");
         

        }

    }

}
