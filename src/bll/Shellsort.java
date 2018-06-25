package bll;

import java.util.ArrayList;

import bll.SortHelper.SORT_TYPE;
import gui.MainFrame;

public class Shellsort extends SortingMethod {

	public Shellsort(MainFrame f, SORT_TYPE sortType) {
		super(f, sortType);
	}

	public void sort(ArrayList<Integer> arrayToSort) {
		int n = arrayToSort.size();
		for (int gap = n / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < n; i += 1) {
				int temp = arrayToSort.get(i);
				int j;
				for (j = i; j >= gap && arrayToSort.get(j - gap) > temp; j -= gap)
					arrayToSort.set(j, arrayToSort.get(j - gap));
				arrayToSort.set(j, temp);
				if (i % 4 == 0)
					f.drawArray(arrayToSort);
				f.incrementSwapCounter();
			}
		}
	}

}
