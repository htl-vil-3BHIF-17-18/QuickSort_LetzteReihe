package bll;

import java.util.ArrayList;

public class SortHelper {

	private static SortHelper instance;

	private SortHelper() {
	}

	public static SortHelper getInstance() {
		if (instance == null) {
			instance = new SortHelper();
		}
		return instance;
	}

	public void quicksort(ArrayList<Integer> arrayToSort, int start, int end) {
		if (start < end) {
			int teiler = quickSortTeile(arrayToSort, start, end);
			quicksort(arrayToSort, start, teiler-1);
			quicksort(arrayToSort, teiler+1, end);
		}
	}

	private int quickSortTeile(ArrayList<Integer> data, int start, int end) {
		int i = start;
		int j = end - 1;
		int pivotElement = data.get(end);

		do {
			while (data.get(i) < pivotElement && i < end - 1) {
				i++;
			}
			while (data.get(j) >= pivotElement && j > start) {
				j--;
			}
			if (i < j) {
				int temp = data.get(i);
				data.set(i, data.get(j));
				data.set(j, temp);
			}
		} while (i < j);
		if(data.get(i) > pivotElement) {
			int temp = data.get(i);
			data.set(i, data.get(end));
			data.set(end, temp);
		}
		return i;
	}

}
