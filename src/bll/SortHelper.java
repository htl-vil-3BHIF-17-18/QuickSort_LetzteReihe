package bll;

import java.util.ArrayList;
import java.util.HashSet;

import gui.MainFrame;

public class SortHelper {

	public enum SORT_TYPE {
		quicksort, heapsort, radixsort, mergesort, insertionsort, bubblesort, shellsort, shakersort
	}

	private SORT_TYPE selectedSort = SORT_TYPE.quicksort;
	private MainFrame f;
	private HashSet<SortingMethod> sortingMethods = new HashSet<SortingMethod>();
	private Thread sortingThread;

	public SortHelper(MainFrame f) {
		this.f = f;
		sortingMethods.add(new Quicksort(f, SORT_TYPE.quicksort));
		sortingMethods.add(new Heapsort(f, SORT_TYPE.heapsort));
		sortingMethods.add(new Radixsort(f, SORT_TYPE.radixsort));
		sortingMethods.add(new Mergesort(f, SORT_TYPE.mergesort));
		sortingMethods.add(new Insertionsort(f, SORT_TYPE.insertionsort));
		sortingMethods.add(new Bubblesort(f, SORT_TYPE.bubblesort));
		sortingMethods.add(new Shellsort(f, SORT_TYPE.shellsort));
		sortingMethods.add(new Shakersort(f, SORT_TYPE.shakersort));
	}

	public void sort(ArrayList<Integer> arrayToSort, Thread t) {
		sortingThread = t;
		for (SortingMethod s : sortingMethods)
			if (s.getSortType() == selectedSort)
				s.sort(arrayToSort);
		f.drawArray(arrayToSort);
		f.enableShuffleBtn();
	}

	public void setSelectedSort(SORT_TYPE selectedSort) {
		this.selectedSort = selectedSort;
	}

	public Thread getSortingThread() {
		return sortingThread;
	}

	public String[] getSortTypes() {
		String[] types = new String[SORT_TYPE.values().length];
		for (int i = 0; i < SORT_TYPE.values().length; i++)
			types[i] = SORT_TYPE.values()[i].toString();
		return types;
	}

}
