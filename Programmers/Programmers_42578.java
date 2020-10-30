package Programmers;

import java.util.HashMap;
import java.util.Map;

public class Programmers_42578 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		String [][] clothes = { {"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"} };
		HashMap <String, Integer> kind = new HashMap<String, Integer>();
		
		//map 안에 값넣어주기
		for(int i=0;i<clothes.length;i++) {
			//map.put(clothes[i][1],clothes[i][0]);
			//System.out.println(kind.containsKey(clothes[i][1]));
			if(kind.containsKey(clothes[i][1])) {
				//System.out.println(kind.get(clothes[i][1]));
				kind.put(clothes[i][1], kind.get(clothes[i][1]) + 1);
				//System.out.println(kind.get(clothes[i][1]));
			} else {
				//System.out.println(clothes[i][1]+"/추가중");
			    kind.put(clothes[i][1],2);
			}
		}
		int mul = 1;
		for(Map.Entry<String,Integer> entry : kind.entrySet()){
			//System.out.println("key : " + entry.getKey() + " , value : " + entry.getValue());
			mul*=entry.getValue();
		}
		System.out.println(mul-1);

	}

}
