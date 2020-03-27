package Day03;
//무한루프란?
//영원히 안끝나는 루프
//무한루프라고 한다. 
public class Ex12InfiniteLoop {

    public static void main(String[] args) {
        //무한루프를 for loop로 만들 수 있지만 while looop로 만드는게  제일 쉽다.
        
        //이클립스 팁:
        //여러줄 주석을 만들고 싶을 때는 
        //주석만들 줄 선택하고
        //ctrl+/ 눌러주면 된다.
        //ctrl+shift+/을 누르게 되면
        // /* */ 형태로 만들어 주긴 하지만 위의 줄을 강제로 한줄로 만드렁 주기 때문에 소스코드를 주석처리할 때는 별로다.
        //반대로 주석을 풀 때는 다시 선택해서
//        ctrl+/또는 ctrl+shift+/을 누르면 풀 수 있다.
        
        
//        1. for loop으로 무한 루프 만들기
//        간단하다. 조건식을 참이 나오는 식을 만들어주면 된다. 
//        for(int i=0;0<1;i++){
//            System.out.println(i);
//        }
//        2.얘도 된다
//        for(;;) {
//            System.out.println("무한루프");
//        }
        //3.while 루프를 통한 무한루프 만들기
//        int count=0;
//        while(count<5) {
//            System.out.println("while루프입니다.");
//        }
        //4.뭐하러 변수까지 만드나
//        while(0<1) {
//            System.out.println("0은 1보다 작아");
//        }
        
        //5.뭐하러 조건식을 만드나
//        while(true) {
//            System.out.println("항상 true");
//        }
    }

}
