package Beak_Jun;

public class Beak_4153 {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(br.readLine());
			int[] A = new int[3]; // 삼각형 변들의 제곱을 넣을 배열
			int max = 0; // 제일 긴 빗변
			int plus = 0; // 모든 빗변 제곱의 합
			for (int i = 0; i < 3; i++) {
				A[i] = Integer.parseInt(st.nextToken());
				A[i] = A[i] * A[i];
				if (max < A[i]) {
					max = A[i];
				}
				plus += A[i];
			}
			if (A[0] == 0 && A[1] == 0 && A[2] == 0) {
				break;
			} else {
				if (plus - (2 * max) == 0) {
					System.out.println("right");
				} else {
					System.out.println("wrong");
				}
			}
		}

	}

}
