package Programmers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Programmers_42579 {

	public static void main(String[] args) {
		String[] genres = {"classic", "pop", "classic", "classic", "pop"};
		int[] plays = {500, 600, 150, 800, 2500};
		
		HashMap<String, Integer> genresCount = new HashMap<String, Integer>();
		HashMap<Integer, Integer> trackPlays = new HashMap<Integer, Integer>();
		int count = 0;
		for(int i=0;i<genres.length;i++) {
			trackPlays.put(plays[i],i);
			if(genresCount.containsKey(genres[i])) {
				//값이 들어있을 경우
				genresCount.put(genres[i], genresCount.get(genres[i])+plays[i]);
			} else {
				//값이 없을 경우
				count++;
				genresCount.put(genres[i], plays[i]);
			}
		}
		int [] answer= new int[count*2];
		count=0;
		//장르에 따른 최대 재생수 넣기
		ArrayList <Object[]> list = new ArrayList<Object[]>();
		for(Map.Entry<String,Integer> entry : genresCount.entrySet()){
			//System.out.println(entry.getKey()+"/"+entry.getValue());
			Object [] arr = new Object[3];
			arr[0]=entry.getKey();
			arr[1]=0;
			arr[2]=0;
			for(int j=0;j<plays.length;j++) {
				if(entry.getKey().equals(genres[j])) {
					if(plays[j]>(int)arr[1]) { 
						arr[2]=arr[1];
						arr[1]=plays[j]; } 
					else if(plays[j]>(int)arr[2]){
						arr[2]=plays[j];
					}
				}
			}
			answer[count]=trackPlays.get(arr[1]);
			answer[count+1]=trackPlays.get(arr[2]);
			
			System.out.println(answer[count]+","+answer[count+1]);
			count+=2;
		}
		
	}

}
