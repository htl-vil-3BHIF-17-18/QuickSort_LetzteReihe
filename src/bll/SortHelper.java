package bll;

import java.util.ArrayList;
import java.util.HashSet;

import gui.MainFrame;

public class SortHelper {

	

	public enum SORT_TYPE {
		quicksort, heapsort, radixsort, mergesort, insertionsort
	}

	private SORT_TYPE selectedSort = SORT_TYPE.quicksort;
	private MainFrame f;
	private HashSet<SortingMethod> sortingMethods = new HashSet<SortingMethod>();
	
	public SortHelper(MainFrame f) {
		this.f = f;
		sortingMethods.add(new Quicksort(f, SORT_TYPE.quicksort));
		sortingMethods.add(new Heapsort(f, SORT_TYPE.heapsort));
		sortingMethods.add(new Radixsort(f, SORT_TYPE.radixsort));
		sortingMethods.add(new Mergesort(f, SORT_TYPE.mergesort));
		sortingMethods.add(new Insertionsort(f, SORT_TYPE.insertionsort));
	}

	public void sort(ArrayList<Integer> arrayToSort) {
		for (SortingMethod s : sortingMethods)
			if (s.getSortType() == selectedSort)
				s.sort(arrayToSort);
		f.drawArray(arrayToSort);
		f.enableShuffleBtn();
	}

	public void setSelectedSort(SORT_TYPE selectedSort) {
		this.selectedSort = selectedSort;
	}

	public String[] getSortTypes() {
		String[] types = new String[SORT_TYPE.values().length];
		for (int i = 0; i < SORT_TYPE.values().length; i++)
			types[i] = SORT_TYPE.values()[i].toString();
		return types;
	}

}
