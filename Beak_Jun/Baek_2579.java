package Beak_Jun;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baek_2579 {
	public static int N;
	public static int[] input;
	public static ArrayList<Integer> stair;
	public static int max;
	public static Boolean calEnd;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = new int[N];
		stair = new ArrayList<Integer>();
		max = 0;
		calEnd=false;
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}
		dfs(0,0);
		System.out.println(max);
	}
	
	//완전탐색법
	private static void dfs(int level, int stairs) {
		if (stairs==N-1) {
			// 계산 끝
			cal();
		} else {
			// 계산중
			int temp = stairs==0&&level==0?0:stairs+1;
			for (int i = temp; i < N; i++) {
				if (check(i)) {
					stair.add(i);
					dfs(level + 1,i);
					stair.remove(level);
				}
			}
		}
	}

	private static void cal() {
		// 최대값 계산
		int temp = 0;
		for (int data : stair) {
			temp += input[data];
		}
	}

	// 계단을 올라도 되는지 여부 확인
	private static boolean check(int i) {
		// 세번 연속 유무 확인
		int size = stair.size();
		if (size >= 2) {
			if (stair.get(size - 1) == i-1 && stair.get(size - 2) == i -2 ) {
				return false;
			}
		}
		// 두 계단 이상인 경우 제어
		if (size > 1) {
			if (stair.get(size - 1) + 2 < i) {
				return false;
			}
		}
		return true;
	}
}
