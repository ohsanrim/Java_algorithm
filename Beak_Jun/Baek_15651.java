package Beak_Jun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baek_15651 {
	static int n;
	static int m;
	static BufferedWriter bw;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");

		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		
		dfs("", 0);
		bw.close();
	}


	private static void dfs(String val, int level) throws IOException {
		if (level >= m) {
			bw.write(val+"\n");
			return;
		} else {
			for (int i = 1; i <= n; i++) {
				dfs(((level == 0) ? Integer.toString(i) : val + " " + i), level + 1);
			}
		}
	}

}
