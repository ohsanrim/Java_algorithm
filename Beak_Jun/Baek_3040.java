package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_3040 {
	public static Boolean[] check;
	public static int [] input;
	public static int [] select;
	public static Boolean end;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = new int[9];
		select = new int[2];
		end=false;
		for(int i=0;i<9;i++) {
			input[i]=Integer.parseInt(br.readLine());
		}
		dfs(0,0);
	}
	//난쟁이 2명만 계산
	public static void dfs(int start, int level) {
		if(level==0) {
			check= new Boolean[9];
			for(int i=0;i<9;i++) {
				check[i]=false;
			}
		}
		if(level<=1) {
			//System.out.println("들어오니?");
			for(int i=start;i<9;i++) {
				if(!check[i]&&!end) {
					select[level]=input[i];
					check[i]=true;
					dfs(i,level+1);
					select[level]=0;
					check[i]=false;
				}
			}
		} else {
			//전부 선택 되었을 때
			int sum =0;
			for(int i=0;i<check.length;i++) {
				if(!check[i]) {
					sum+=input[i];
				}
			}
			//System.out.println(sum);
			if(sum==100) {
				for(int i=0;i<check.length;i++) {
					if(!check[i]) {
						System.out.println(input[i]);
					}
				}
				end=true;
			}
		}
	}

}
