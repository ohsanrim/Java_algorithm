package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baek_1181 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<String> li = new ArrayList<String>();

        for (int i = 0; i < N; i++) { // 배열에 String담기
            li.add(br.readLine());
        }
        li = quickSort(li); // 정렬 실행
        for (int i = 0; i < li.size(); i++) {
            System.out.println(li.get(i));
        }
    }

    public static ArrayList<String> quickSort(ArrayList<String> li) {
        // arr을 start부터 end까지 퀵정렬 하는 함수

        if (li.size() < 2) {// 첫 값이 끝 값보다 항상 작아야 함
            return li;
        }
        ArrayList<String> left = new ArrayList<String>(); // 작은 값을 담을 좌 list
        ArrayList<String> right = new ArrayList<String>();// 큰 값을 넣을 우 list
        String middle = li.get(li.size() / 2); // 제일 중앙 글자를 저장
        int pivot = middle.length(); // 제일 중앙값의 글자의 길이

        li.remove(li.size() / 2);

        for (int i = 0; i < li.size(); i++) {
            if (li.get(i).length() <= pivot) {
                if (li.get(i).length() == pivot) { // 민약 길이가 같을경우
                    for (int j = 0; j < li.get(i).length(); j++) {
                        if (li.get(i).charAt(j) > middle.charAt(j)) {
                            right.add(li.get(i));
                            break;
                        } else if (li.get(i).charAt(j) < middle.charAt(j)) {
                            left.add(li.get(i));
                            break;
                        }
                    }
                } else {
                    left.add(li.get(i)); // 만약 배열의 숫자가 pivot보다 클 때 right에 저장
                }
            } else {
                right.add(li.get(i)); // 만약 배열의 숫자가 pivot보다 작을 때 left에 저장
            }
        }
        left = quickSort(left);
        right = quickSort(right);
        ArrayList<String> sortedList = new ArrayList<String>();
        sortedList.addAll(left);
        sortedList.add(middle);
        sortedList.addAll(right);
        return sortedList;
    }
}

