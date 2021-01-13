package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_14890 {
	static int n, l;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		l = Integer.parseInt(temp[1]);
		map = new int[n][n];
		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			temp = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(temp[j]);
			}
		}
		System.out.println(cul()+row());
	}
	private static int cul() {
		// TODO Auto-generated method stub
		
		int answer = 0;
		for (int i = 0; i < n; i++) {
			int back = map[0][i];
			int cnt = 0;
			boolean check = true;
			for (int j = 0; j < n; j++) {
				if(!check) {
					//clear();
					break;
				}
				if (back != map[j][i]) {
					if (map[j][i] - 1 == back) {
						// 오르막길 만났을 경우
						
						for (int k = j - 1; k > j - 1 - l; k--) {
						//	System.out.println("오르막길" + i + " " + k + " " + back);
							if (!isIn(i, k) || visited[k][i] || back != map[k][i]) {
						//		System.out.println("오르막길 체크 실패" + i + " " + k + " " + back);
								check = false;
								break;
							}
							visited[k][i] = true;

						}
					} else if (map[j][i] + 1 == back) {
						int temp = j;
						// 내리막길
						for (int k = temp; k < temp+l ; k++) {
						//	System.out.println("내리막길 체크 " + i + " " + k+" "+back);
							if (!isIn(i, k) || visited[k][i]|| back-1 != map[k][i]) {
							//	System.out.println("내리막길 체크 실패" + i + " " + k+" "+back);
								check = false; // 경사로 못놓을 경우
								break;

							}
							j = k;
							visited[k][i] = true;
						}
						back= map[j][i];
					} else {
					//	System.out.println("못건너감");
						check = false; // 건너갈 수 없음
					}
				}
				back = map[j][i];
			}
			if (check)
				answer++;
		}
		//print();
		return answer;
	}

	private static int row() {
		// TODO Auto-generated method stub
		visited = new boolean[n][n];
		int answer = 0;
		for (int i = 0; i < n; i++) {
			int back = map[i][0];
			int cnt = 0;
			boolean check = true;
			for (int j = 0; j < n; j++) {
				if(!check) {
					for(int k=0;k<n;k++) {
						visited[i][j]=false;
					}
					break;
				}
				if (back != map[i][j]) {
					if (map[i][j] - 1 == back) {
						// 오르막길 만났을 경우
						for (int k = j - 1; k > j - 1 - l; k--) {
							if (!isIn(i, k) || visited[i][k] || back != map[i][k]) {
							//	System.out.println("오르막길 체크 실패" + i + " " + k + " " + back);
								check = false;
								break;
							}
							visited[i][k] = true;

						}
					} else if (map[i][j] + 1 == back) {
						int temp = j;
						// 내리막길
						for (int k = temp; k < temp+l ; k++) {
					//		System.out.println("내리막길 체크 " + i + " " + k+" "+back);
							if (!isIn(i, k) || back-1 != map[i][k]) {
					//			System.out.println("내리막길 체크 실패" + i + " " + k+" "+back);
								check = false; // 경사로 못놓을 경우
								break;

							}
							j = k;
							visited[i][k] = true;

						}
						back= map[i][j];
					} else {
					//	System.out.println("못건너감");
						check = false; // 건너갈 수 없음
					}
				}
				back = map[i][j];
			}
			if (check)
				answer++;
		}
		//print();
		return answer;
	}

	private static boolean isIn(int x, int y) {
		// TODO Auto-generated method stub
		return x >= 0 && y >= 0 && x < n && y < n;
	}

}
/*
private static void print() {
	// TODO Auto-generated method stub
	for(boolean [] arr : visited) {
		for(boolean data : arr) {
			System.out.print(data+" ");
		}
		System.out.println();
	}
}
6 2
3 3 3 2 1 1
3 3 3 2 2 1
3 3 3 3 3 2
2 2 3 2 2 2
2 2 3 2 2 2
2 2 2 2 2 2
//답: 7
 * 
4 3
2 1 2 1
1 1 1 1
1 1 1 1
1 1 1 1
 */
