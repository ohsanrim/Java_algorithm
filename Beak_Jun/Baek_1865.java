package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baek_1865 {
	//벨만 포드 방법 적용
	static int n,m,w;
	static int INF = Integer.MAX_VALUE;
	static int [] dis;
	static ArrayList<Node>[] list; 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for(int i=0;i<tc;i++) {
			String [] temp = br.readLine().split(" ");
			n = Integer.parseInt(temp[0]);
			m = Integer.parseInt(temp[1]);
			w = Integer.parseInt(temp[2]);
			
			list = new ArrayList[n+1];
			for(int j=0;j<n+1;j++) {
				list[j]=new ArrayList<>();
			}
			//도로 정보 갱신
			for(int j=0;j<m;j++) {
				temp = br.readLine().split(" ");
				int s = Integer.parseInt(temp[0]);
				int e = Integer.parseInt(temp[1]);
				int t = Integer.parseInt(temp[2]);
				list[s].add(new Node(e,t));
				list[e].add(new Node(s,t));
			}
			//웜홀 정보
			for(int j=0;j<w;j++) {
				temp = br.readLine().split(" ");
				int s = Integer.parseInt(temp[0]);
				int e = Integer.parseInt(temp[1]);
				int t = Integer.parseInt(temp[2]);
				list[s].add(new Node(e,-t));
			}
			System.out.println(bellmanFord()?"YES":"NO");
		}
	}
	
	private static boolean bellmanFord() {
		//모든 좌표가 시작좌표!! 전부 검사해야 함
		for(int i=1;i<n+1;i++) {
			boolean check=false;
			clear();
			dis[i]=0;
			for(int j=1;j<n+1;j++) {
				check=false;
				for(int k=1;k<n+1;k++) {
					for(Node node :list[k]) {
						if(dis[k]!=INF && dis[k]+node.t<dis[node.index]) {
							//최소값 갱신
							dis[node.index]=dis[k]+node.t;
							check=true;
						}
					}
				}
				if(!check) {
					//갱신이 더이상 일어나지 않을 경우(음의 값 영향 X)
					break;
				}
			}
			if(check) {
				//최소값 갱신이 계속 일어남(루트에 웜홀 존재)
				return true;
			}
		}
		return false;
	}

	private static void clear() {
		dis = new int[n+1];
		for(int i=0;i<n+1;i++) {
			dis[i]=INF;
		}
	}

	private static class Node{
		int index; 
		int t; // 소요시간

		Node(int index, int t) {
			this.index = index;
			this.t = t;
		}
	}
}
