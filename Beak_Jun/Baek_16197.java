package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//9시
public class Baek_16197 {
	static int n, m;
	static String[][] map;
	static int[][] move = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);
		map = new String[n][m];
		ArrayList<Coin> list = new ArrayList<>();
		Coin[] coin = new Coin[2];
		int coinN = 0;
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().split("");
			for (int j = 0; j < m; j++) {
				if (map[i][j].equals("o")) {
					coin[coinN++] = new Coin(i, j);
				}
			}
		}
		System.out.println(bfs(coin));
	}

	private static int bfs(Coin[] coin) {
		Queue<Coin[]> q = new LinkedList<>();
		q.add(coin);

		for (int time = 0; !q.isEmpty(); time++) {
			if(time>=10) return -1;
			int q_size=q.size();
			for(int k=0;k<q_size;k++) {
				Coin[] c = q.poll();
				for (int i = 0; i < 4; i++) {
					int fall = 0;
					boolean check=false;
					Coin [] temp = new Coin[2];
					
					for(int j=0;j<2;j++) {
						int x = c[j].x + move[i][0];
						int y = c[j].y + move[i][1];
						 if(isIn(x,y) && !map[x][y].equals("#")){
							//벽이 아니고, 범위 안 일경우
							temp[j] = new Coin(x,y); 
							check=true;
						} else if(!isIn(x,y)){
							fall++;
						} else if(map[x][y].equals("#")) {
							temp[j]=new Coin(c[j].x,c[j].y);
						}
					}
					if(fall==1) {  //하나만 남았을 경우
						return time+1;
					} else if(check) {
						//한번이라도 움직인 경우
						q.add(temp);
					}
				}
			}
		}
		return -1;
	}

	private static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x<n && y<m;
	}

	private static class Coin {
		int x, y;

		Coin(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}

/*
13 2
##
o#
o#
.#
.#
.#
.#
.#
.#
.#
.#
.#
.#

*/

