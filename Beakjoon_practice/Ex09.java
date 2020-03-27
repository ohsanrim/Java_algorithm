package Day03;
//2중 for loop을 이용하여 1~100까지의 소수를 구하는 프로그램을 만들어 보자
//1과 자기자신 외에는 약수가 없는 숫자
//약수란 무엇인가
//A를 B로 나누었을 때 나누어 떨어지면 Bㄴ는 A의 약수이다. 
//그러면 어떤 숫자의 가장 큰 약수는 바로 자기자신
//이 3가지를 이용하여 1~100 까지의 약수를 구하는 프로그램을 만들본자

public class Ex09 {

    public static void main(String[] args) {
       for(int i=1;i<=100;i++) {
           int count =0;
           //카운트는 약수의 갯수를 저장한다. 
           for(int j=1;j<=i;j++) {
               if(i%j==0) {
                   count++;
               }
           }
           if(count==2) {
               System.out.println(i+"는 소수입니다.");
               
           }
       }

    }

}
