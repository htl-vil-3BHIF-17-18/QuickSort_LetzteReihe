package bll;

import java.util.ArrayList;

import bll.SortHelper.SORT_TYPE;
import gui.MainFrame;

public class Insertionsort extends SortingMethod {

	public Insertionsort(MainFrame f, SORT_TYPE sortType) {
		super(f, sortType);
	}

	public void sort(ArrayList<Integer> arrayToSort) {
		int n = arrayToSort.size();
		for (int i = 1; i < n; ++i) {
			int key = arrayToSort.get(i);
			int j = i - 1;
			while (j >= 0 && arrayToSort.get(j) > key) {
				arrayToSort.set(j+1, arrayToSort.get(j));
				f.drawArray(arrayToSort);
				j = j - 1;
			}
			arrayToSort.set(j+1, key);
			f.drawArray(arrayToSort);
		}
	}
}
