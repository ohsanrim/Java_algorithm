package Beak_Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Baek_7785 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		HashMap<String, String> inout = new HashMap<String, String>();
		for (int i = 0; i < num; i++) {
			String[] temp = br.readLine().split(" ");
			String name = temp[0];
			String inoutRecord = temp[1];
			inout.put(name, inoutRecord);
		}
		ArrayList<String> arr = new ArrayList<String>();

		for (Map.Entry<String, String> entry : inout.entrySet()) {
			if (entry.getValue().equals("enter")) {
				arr.add(entry.getKey());
			}
		}
		sort(arr);
		for (String name : arr) {
			System.out.println(name);
		}
	}

	public static ArrayList<String> sort(ArrayList<String> arr) {
		Collections.sort(arr, new AscendingString());
		return arr;
	}

}
class AscendingString implements Comparator<String> {
	@Override
	public int compare(String a, String b) {
		return b.compareTo(a);
	}
}
