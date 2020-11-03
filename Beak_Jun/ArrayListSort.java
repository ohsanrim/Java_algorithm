package Beak_Jun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ArrayListSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

ArrayList <Integer> integerlist= new ArrayList <Integer>();
integerlist.add(200);
integerlist.add(30);
integerlist.add(45);
integerlist.add(350);
integerlist.add(15);

ArrayList <String> stringList= new ArrayList <String>();
stringList.add("camera");
stringList.add("cat");
stringList.add("ArrayList");
stringList.add("Apple");
stringList.add("trip");
		
		
Collections.sort(integerlist);
Collections.sort(stringList);
		/*
		 * for (int data : integerlist) { System.out.println(data); }
		 */
for (String data : stringList) {
	System.out.println(data);
}
	}
	class AscendingInteger implements Comparator<Integer> {
		@Override
		public int compare(Integer a, Integer b) {
			return b.compareTo(a);
		}
	}

	class AscendingString implements Comparator<String> {
		@Override
		public int compare(String a, String b) {
			return b.compareTo(a);
		}
	}

}
