package bll;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import gui.MainFrame;

public class SortHelper {

	private static SortHelper instance;
	private static MainFrame f;

	public enum SORT_TYPE {
		quicksort, heapsort
	}

	private SORT_TYPE selectedSort = SORT_TYPE.quicksort;

	private SortHelper() {
	}

	public void sort(ArrayList<Integer> arrayToSort) {
		if (selectedSort == SORT_TYPE.quicksort) {
			quicksort(arrayToSort, 0, arrayToSort.size() - 1);
		} else if (selectedSort == SORT_TYPE.heapsort) {
			heapsort(arrayToSort);
		}
	}

	public static SortHelper getInstance(MainFrame frame) {
		if (instance == null) {
			instance = new SortHelper();
		}
		f = frame;
		return instance;
	}

	////////////// QUICKSORT \\\\\\\\\\\\\\\
	private void quicksort(ArrayList<Integer> arrayToSort, int left, int right) {
		int index = quickSortTeile(arrayToSort, left, right);
		if (left < index - 1)
			quicksort(arrayToSort, left, index - 1);
		if (index < right)
			quicksort(arrayToSort, index, right);
		f.drawArray(arrayToSort);
	}

	private int quickSortTeile(ArrayList<Integer> data, int left, int right) {
		int i = left;
		int j = right;
		int pivotElement = data.get((left + right) / 2); // pivot element ist
															// die mitte des
															// array

		while (i <= j) {
			while (data.get(i) < pivotElement)
				i++;
			while (data.get(j) > pivotElement)
				j--;
			if (i <= j) {
				if (i != j) {
					HashMap<Integer, Color> hashmap = new HashMap<Integer, Color>();
					hashmap.put(i, Color.red);
					hashmap.put(j, Color.red);
					hashmap.put(data.indexOf(pivotElement), Color.blue);
					f.drawArray(data, hashmap);
					switchElements(data, i, j);
				}
				i++;
				j--;
			}
		}
		return i;
	}



	////////////// HEAPSORT \\\\\\\\\\\\\\\
	private void heapsort(ArrayList<Integer> array) {
		int length = array.size();

		buildMaxHeap(array, length);
		for (int i = length - 1; i > 0; i--) {
			HashMap<Integer, Color> hashmap = new HashMap<Integer, Color>();
			hashmap.put(i, Color.red);
			hashmap.put(0, Color.red);
			f.drawArray(array);
			switchElements(array, i, 0);
			maxHeapify(array, 1, i);
		}
	}

	private void buildMaxHeap(ArrayList<Integer> array, int heapSize) {
		if (array == null) {
			throw new NullPointerException("null");
		}
		if (array.size() <= 0 || heapSize <= 0) {
			throw new IllegalArgumentException("illegal");
		}
		if (heapSize > array.size()) {
			heapSize = array.size();
		}

		for (int i = heapSize / 2; i > 0; i--) {
			maxHeapify(array, i, heapSize);
		}
	}

	private void maxHeapify(ArrayList<Integer> array, int index, int heapSize) {
		int l = index * 2;
		int r = l + 1;
		int largest;

		if (l <= heapSize && array.get(l - 1) > array.get(index - 1)) {
			largest = l;
		} else {
			largest = index;
		}

		if (r <= heapSize && array.get(r - 1) > array.get(largest - 1)) {
			largest = r;
		}

		if (largest != index) {
			HashMap<Integer, Color> hashmap = new HashMap<Integer, Color>();
			hashmap.put(index - 1, Color.red);
			hashmap.put(largest - 1, Color.red);
			f.drawArray(array, hashmap);
			switchElements(array, index - 1, largest - 1);
			maxHeapify(array, largest, heapSize);
		}
	}

	private void switchElements(ArrayList<Integer> list, int index1, int index2) {
		int temp = list.get(index1);
		list.set(index1, list.get(index2));
		list.set(index2, temp);
		f.incrementSwapCounter();
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
