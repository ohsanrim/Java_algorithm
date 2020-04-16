package Beak_Jun;

public class Programmers_42584 {
	public static void main(String[] args) {
		int[] prices= {1,2,3,2,3};
		Solution s = new Solution();
		int answer[] = s.solution(prices);
		
	}
	
}
class Solution {
	    public int[] solution(int[] prices) {
	        int[] answer = new int[prices.length];
	        int countSecond = 0;
	        //전체 횟수
	        for(int i=0;i<prices.length;i++) {
	            //각 번호 별로 
	            for(int j=i;j<prices.length;j++) {
	                 countSecond++;
	                if(prices[i]>prices[j]) {
	                    break;
	                }
	            }
	            answer[i]=countSecond-1;
	            countSecond=0;
	        }
	        return answer;
	    }
	}
