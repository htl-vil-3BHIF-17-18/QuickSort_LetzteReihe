package bll;

import java.util.ArrayList;

import bll.SortHelper.SORT_TYPE;
import gui.MainFrame;

public class Bubblesort extends SortingMethod {

	public Bubblesort(MainFrame f, SORT_TYPE sortType) {
		super(f, sortType);
	}

	public void sort(ArrayList<Integer> arrayToSort) {
		boolean switched;
		do {
			switched = false;
			for(int i = 0; i < arrayToSort.size(); i++) {
				if(i != arrayToSort.size()-1) {
					if(arrayToSort.get(i) > arrayToSort.get(i+1)) {
						switchElements(arrayToSort, i, i+1);
						
						switched = true;
					}
				}
			}
			f.drawArray(arrayToSort);
		} while (switched);
	}

}
