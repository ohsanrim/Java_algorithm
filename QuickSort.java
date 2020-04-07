package util;

import java.util.ArrayList;

public class QuickSort {
    public static ArrayList<Integer> quickSort(ArrayList<Integer> li) {
        // arr을 start부터 end까지 퀵정렬 하는 함수
        
        if (li.size()<2) {// 첫 값이 끝 값보다 항상 작아야 함
          return li;   
        }
            ArrayList<Integer> left = new ArrayList<>(); // 작은 값을 담을 좌 list
            ArrayList<Integer> right = new ArrayList<>();// 큰 값을 넣을 우 list

            int pivot = li.get(li.size()/2);
            li.remove(li.size()/2);
            
            for (int i = 0; i < li.size(); i++) {
                if (li.get(i) <= pivot) {
                    left.add(li.get(i)); // 만약 배열의 숫자가 pivot보다 클 때 right에 저장

                } else {
                    right.add(li.get(i)); // 만약 배열의 숫자가 pivot보다 작을 때 left에 저장
                 
                }
            }
            left= quickSort(left);
            right= quickSort(right);
            ArrayList <Integer> sortedList = new ArrayList<>();
            sortedList.addAll(left);
            sortedList.add(pivot);
            sortedList.addAll(right);
            
       
        return  sortedList;

    }
    // pivot값을 구하기

}
