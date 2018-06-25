package bll;

import java.util.ArrayList;

import bll.SortHelper.SORT_TYPE;
import gui.MainFrame;

public class Shakersort extends SortingMethod {

	public Shakersort(MainFrame f, SORT_TYPE sortType) {
		super(f, sortType);
	}

	public void sort(ArrayList<Integer> arrayToSort) {
		for (int i = 0; i < arrayToSort.size() / 2; i++) {
			boolean swapped = false;
			for (int j = i; j < arrayToSort.size() - i - 1; j++) {
				if (arrayToSort.get(j) > arrayToSort.get(j + 1)) {
					switchElements(arrayToSort, j, j + 1);
					swapped = true;
				}
			}
			for (int j = arrayToSort.size() - 2 - i; j > i; j--) {
				if (arrayToSort.get(j) < arrayToSort.get(j - 1)) {
					switchElements(arrayToSort, j, j - 1);
					swapped = true;
				}
			}
			if (!swapped)
				break;
			f.drawArray(arrayToSort);
		}

	}

}
