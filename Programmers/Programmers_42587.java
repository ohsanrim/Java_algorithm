package Programmers;

public class Programmers_42587 {

	public static void main(String[] args) {
		Solution s = new Solution();
		int [] priorities = new int[] {1,1,1,9,1,1,1};
		int location=0;
		int answer = s.solution(priorities, location);
	}
	public static class Solution {
	    public int solution(int[] priorities, int location) {
	        int answer = 0;
	        int count=0; //출력횟수
	          while(answer==0) {
	              int temp[] = new int[priorities.length];
	              for(int i=1;i<temp.length;i++) {
	                  if(priorities[0]<priorities[i]) { //큰 값이 있을 때
	                      temp[priorities.length-1]=priorities[0];
	                      for(int j=0;j<temp.length-1;j++) {
	                          temp[j]=priorities[j+1];
	                      }
	                      priorities=temp;
	                      location--;
	                      
	                      if(location<0) {
	                          location=priorities.length-1;  //끝 값으로 초기화
	                      }
	                     break;
	                  }
	                  if(i==temp.length-1) {
	                      count++;
	                      //System.out.println(count+" "+priorities[0]);
	                      priorities[0]=0; //0초기화
	                      
	                      if(location==0) {
	                          answer=count;
	                          break;
	                      }
	                  }
	              }
	          }
	        return answer;
	    }
	}
}
	

