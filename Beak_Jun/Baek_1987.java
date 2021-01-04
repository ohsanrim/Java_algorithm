package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashSet;

public class Baek_1987 {
	static int r,c;
	//static boolean [][] visited;
	static CharSequence [][] map;
	static HashSet<CharSequence> hs;
	static int [][] move ={{-1,0},{0,-1},{1,0},{0,1}};
	static int count=1;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String temp[] = br.readLine().split(" ");
		r = Integer.parseInt(temp[0]);
		c = Integer.parseInt(temp[1]);
		hs = new HashSet<>();
		map = new CharSequence[r][c];
	//	visited = new boolean [r][c];
		for(int i=0;i<r;i++) {
			temp = br.readLine().split("");
			for(int j=0;j<temp.length;j++) {
				map[i][j]=temp[j];
			}
		}
		hs.add(map[0][0]);
		//visited[0][0] = true;
		dfs(0,0,1);
		System.out.println(count);
	}

	private static void dfs(int px, int py, int cnt) {
		// TODO Auto-generated method stub
		for(int i=0;i<4;i++) {
			int x = px+move[i][0];
			int y = py+move[i][1];
			//System.out.println(x+" "+y);
			//if(isIn(x,y) && !visited[x][y]) {
			if(isIn(x,y)) {
				if(check(map[x][y])) {
					//겹치는 경우
					count = Math.max(count, cnt);
				}else {
					//진행 가능
			//		visited[x][y]=true;
					hs.add(map[x][y]);
					//System.out.println("이동:"+x+" "+y+" "+(cnt+1));
					dfs(x,y,cnt+1);
					hs.remove(map[x][y]);
				//	visited[x][y]=false;
				}
			}
		}
		
	}

	private static boolean check(CharSequence c) {
		//System.out.println(c+" 체크중");
		if(hs.contains(c)) {
			return true;
		}
		return false;
	}

	private static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x<r && y<c;
	}
}
/*
2 4
CAAB
CDCB

4 4
ABCD
EFAD
ASDA
NGHZ

3 4
CABD
CABG
CABH

10 10
ASWERHGCFH
QWERHDLKDG
ZKFOWOHKRK
SALTPWOKSS
BMDLKLKDKF
ALSKEMFLFQ
GMHMBPTIYU
DMNKJZKQLF
HKFKGLKEOL
OTOJKNKRMW

1 1
A 

20 20 
ZYXWVUTSRQPONMLKJIHG
YXWVUTSRQPONMLKJIHGF
XWVUTSRQPONMLKJIHGFE
WVUTSRQPONMLKJIHGFE
VUTSRQPONMLKJIHGFEDC
UTSRQPONMLKJIHGFEDCB
TSRQPONMLKJIHGFEDCB
SRQPONMLKJIHGFEDCBAA
RQPONMLKJIHGFEDCBAAA
QPONMLKJIHGFEDCBAAAA
PONMLKJIHGFEDCBAAAA
ONMLKJIHGFEDCBAAAAAA
NMLKJIHGFEDCBAAAAAAA
MLKJIHGFEDCBAAAAAAAA
LKJIHGFEDCBAAAAAAAAA
KJIHGFEDCBAAAAAAAAAA
JIHGFEDCBAAAAAAAAAAA
IHGFEDCBAAAAAAAAAAAA
HGFEDCBAAAAAAAAAAAAA
GFEDCBAAAAAAAAAAAAAA
*/
