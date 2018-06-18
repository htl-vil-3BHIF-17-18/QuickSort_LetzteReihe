package app;

import java.util.ArrayList;
import java.util.Random;

import bll.SortHelper;

public class Start {

	public static void main(String[] args) {
		System.out.println("Hello World");
		SortHelper sh = SortHelper.getInstance();
		ArrayList<Integer> a = new ArrayList<Integer>();
		for(int i = 0; i < 10; i++) {
			a.add(new Random().nextInt(100));
		}
		System.out.println(a.toString());
		sh.quicksort(a, 0, a.size()-1);
		System.out.println(a.toString());
	}

}
