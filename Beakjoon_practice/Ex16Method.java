package Day03;
//메소드란 우리가 어떠한 값을 주고 그리고 그값을 년산해서 해당 메소드가 리턴된 곳으로 그 결과값을 보내주는 기능이다. 

//c에서는 function혹은 함수라고 부리지만 자바에서는 메소드라고 한다.

public class Ex16Method {
//메소드의 선언
    // (접근제한자 static)returnType name(parameter)
    // 접근제한자: access modifier
    // 접근제한자란, 해당 메소드 혹은 필드가 어디서 접근이 가능한 범위를 적어준다.
    // public > protected > defalult > private
    // public: "공공의"
    // 패키지 내, 외부, 다른 클래스 어디서든 호출 가능
    // protected: "보호된"같은 패키지와 상속받는 자식 클래스들은 호출 가능
    // default: "기본의", 같은 패키지 내부에서만 호출 가능
    //          만약 아무것도 안적어주면 default가 적용된다.
    // private: "사적인", 같은 클래스만 접근 가능하다.
    
    
    // static:해당메소드를 메소드 영역에 등록
    // 만약 스태틱 메소드가 다른 메소드를 호출하게 되면 그 다른 메소드를 객체생성해서 호출하는 것이 아니면 그 다른 메소드도 Static이
    // 붙어야 함
    //
    // returnType: 해당 메소드가 끝나도 호출된 곳으로 보내줄 값의 datatype
    //             만약에 우리가 숫자를 줘서 그 숫자의 합을 구하는 메소드가 있다고 가정하면 해당메소드의 리턴타입은 당연히 int가 된다.
    //             하지만 만약 해당 메소드가 아무런 값도 리턴하지 않는다면 "void"라고 적어주면 된다.
    // name: 소문자+동사
    //       nextInt(), NextLine(); 등과 같은 원리
    // parameter: 이 메소드가 호출될 때 외부에서 보내주는 값(datatype name)
    //            만약 해당 메소드가 호출될 ㄷ때 아무런 값도 필요없다면 아무것도 안적어줘도 된다.
    //            또한파라미터는 일종의 변수취급되어서 해당 메소드 내부에서는 우리가 똑같은 이름의 변수를 만들어 줄 수 없다.
    //            그리고 메소드를 호출한 곳에서는 굳이 똑같은 이름으로 값을 보내줄 필요가 없다.
    
    // 사실 여러분들이 어떤ㅁ 무언가를 봤을 때 메소드인지 아닌지는 엄청 쉽게 알아차릴 수 있다
    //어떻게?
    //괄호가 붙어있으면 무조건 메소드!!!!!!!!!!
    //우리가 한번 더하기를 해주는 메소드를 직접 만들어보자
    
    public static void main(String[] args) {
        int userNumber1 =10;
        int userNunber2 =25;
        //메소드를 호출할 때는 우리가 파라미터를 메소드에 선언한 그대로 적어주어야 한다. 
        //만약 우리가 add(int a, double b)라고 했는데 
        //add(5,10)이라고 입력하면 에러가 난다. 
        //왜냐면 10은 int이지 double이 아니기 때문
       
        int add = add(userNumber1,userNunber2);
        int subtract = subtract(userNumber1,userNunber2);
        int multiply = multiply(userNumber1,userNunber2);
        int divide = divide(userNumber1,userNunber2);
        //그리고 우리가 설정한 이름은 username,이지만 메소드 내부에서 입력받을 땐 a로 입력받음 
        //때문에 굳이 이름이 안 똑같아도됨
        
        System.out.println(add);
        System.out.println(subtract);
        System.out.println(multiply);
        System.out.println(divide);

    }
    public static int add(int a, int b) {//메인 메소드에서 호출할 것이므로 static을 붙여준다.
        //리턴은 reeturn이라는 명령어로 한다.
        return a+b;
    }
    //-,*,/까지 만들어보기
    public static int subtract(int a, int b) {
        return a-b;
    }
    public static int multiply(int a, int b) {
        return a*b;
    } 
    public static int divide(int a, int b) {
        return a/b;
    }



}
